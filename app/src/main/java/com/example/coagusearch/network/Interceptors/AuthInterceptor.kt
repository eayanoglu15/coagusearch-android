package com.example.coagusearch.network.Interceptors


import android.content.Context
import android.util.Log
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.utils.PreferenceHelper
import com.example.coagusearch.utils.securelyGetString
import okhttp3.*
import java.net.SocketTimeoutException
import com.example.coagusearch.Constants
import com.example.coagusearch.typing.expect

//TODO: Force User to Logout
class AuthInterceptor(
        private val context: Context
) : Interceptor {
    private lateinit var authRepository: AuthRepository
    private val UNAUTHORIZED_CODE = 401
    private val FORBIDDEN_CODE = 403
    private val UPDATE_REQUIRE_CODE = 418
    private val DASHBOARD_UPDATE = "x-dashboard-update"
    private val TRUE = "true"

    fun initAuthRepository(authRepository: AuthRepository) {
        println("initiliazed Auth Repository")
        this.authRepository = authRepository
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            if (request.header("RequireAuth").isNullOrBlank().not()) {
                val sharedPref = PreferenceHelper.defaultPrefs(context)

                var tokenType = sharedPref.securelyGetString(context, Constants.SHARED_PREF_TOKEN_TYPE)
                var accessToken = sharedPref.securelyGetString(context, Constants.SHARED_PREF_ACCESS_TOKEN)

                if (tokenType.isNullOrBlank() || accessToken.isNullOrBlank()) {
                    return nonAuthRequest(chain)
                }

                val response = authRequest(chain, tokenType, accessToken)


                return if (response.code() == UNAUTHORIZED_CODE) {
                    println("Unauthroized came")
                    val refreshToken = sharedPref.securelyGetString(context, Constants.SHARED_PREF_REFRESH_TOKEN)
                    if (refreshToken.isNullOrBlank())
                        return response

                    val refreshTokenResult = authRepository.refresh(refreshToken)
                    println("refresh token $refreshTokenResult")
                    // Check if the refresh token request has an error
                    if (refreshTokenResult !in 200..299) {
                        if (refreshTokenResult == UNAUTHORIZED_CODE || refreshTokenResult == FORBIDDEN_CODE) {
                            Log.d("HospitalOnMobile", "user will be forced to logout")
                            response.close()
                        }
                        // Return the response of the original request
                        response
                    } else {
                        // Get the new tokens
                        tokenType = sharedPref.securelyGetString(context, Constants.SHARED_PREF_TOKEN_TYPE)
                        accessToken = sharedPref.securelyGetString(context, Constants.SHARED_PREF_ACCESS_TOKEN)

                        if (tokenType.isNullOrBlank() || accessToken.isNullOrBlank()) {
                            return response
                        }

                        response.close()
                        // Create a new request and return its response
                        val newResponse = authRequest(chain, tokenType, accessToken)
                        newResponse
                    }
                } else if (response.code() == UPDATE_REQUIRE_CODE) {
                    Log.d("HospitalOnMobile", "user must update this application")

                    response
                } else {
                    response
                }
            } else {

                val response = nonAuthRequest(chain)
                if (response.code() == UPDATE_REQUIRE_CODE) {
                    Log.d("HospitalOnMobile", "user must update this application")
                    response.close()
                }

                return response
            }
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            return mockTimeoutRequest(chain)
        } catch (e: Exception) {
            e.printStackTrace()
            return mockExceptionRequest(chain)
        }
    }


    private fun authRequest(chain: Interceptor.Chain, tokenType: String, accessToken: String): Response {
        val newRequest = chain.request().newBuilder()
                .removeHeader("RequireAuth")
                .addHeader("Authorization", "$tokenType $accessToken")
                .build()
        return chain.proceed(newRequest)
    }

    private fun nonAuthRequest(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
                .removeHeader("RequireAuth")
                .build()
        return chain.proceed(newRequest)
    }

    private fun mockTimeoutRequest(chain: Interceptor.Chain): Response {
        return Response.Builder()
                .code(408)
                .protocol(Protocol.HTTP_2)
                .message("")
                .body(ResponseBody.create(MediaType.parse("application/json"), ""))
                .request(chain.request())
                .build()
    }

    private fun mockExceptionRequest(chain: Interceptor.Chain): Response {
        return Response.Builder()
                .code(444)
                .protocol(Protocol.HTTP_2)
                .message("")
                .body(ResponseBody.create(MediaType.parse("application/json"), ""))
                .request(chain.request())
                .build()
    }
}