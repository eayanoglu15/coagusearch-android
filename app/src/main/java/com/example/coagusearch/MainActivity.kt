package com.example.coagusearch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.appModule
import kotlinx.android.synthetic.main.loginscreen.*
import okhttp3.OkHttpClient
import okhttp3.Protocol
import org.koin.android.ext.android.inject

import org.koin.core.context.startKoin


class MainActivity: AppCompatActivity(){
    private val authInterceptor: AuthInterceptor by inject()
    private val authRepository: AuthRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Koin Dependency Injection with modules from [appModule]
        startKoin{
             listOf(appModule)
        }
            // Force init AuthRepository
            //val authRepository = AuthRepository(applicationContext, authInterceptor)

            val response = authRepository.signIn("14051222123","123456")
            println(response?.accessToken)
            val userRepository= UsersRepository(this)
            setContentView(R.layout.loginscreen)

            LoginButton.setOnClickListener { if (!isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error="Password is not valid"
            } else {
                // Clear the error.
                userRepository.getUserInfo()
                //val intent = Intent(this,main::class.java)
                //startActivity(intent)
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            } }
            PasswordInput.setOnKeyListener { _, _, _ ->
                if (isPasswordValid(PasswordInput.text!!)) {
                    PasswordInput.error = null
                }
                false
            }
        }



    private fun isPasswordValid(text: Editable?): Boolean {
        //return text != null && text.length >= 8
        return true
    }
}


