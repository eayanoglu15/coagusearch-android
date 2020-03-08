package com.example.coagusearch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.api.AuthApi
import com.example.coagusearch.entity.Auth
import com.example.coagusearch.entity.LoginRequest
import kotlinx.android.synthetic.main.loginscreen.*
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)
        val okHttpClientBuilder = OkHttpClient.Builder()
        print("\n\n\n\nugur ulas\n\n\n\n")
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val httpClient = okHttpClientBuilder
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.16.132.112:8080/")
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(AuthApi::class.java)

          var aa= service.signIn(LoginRequest("345678754","123456"))
           aa.enqueue(object :Callback<Auth>{
               override fun onFailure(call: Call<Auth>, t: Throwable) {
                   LoginButton.text="fail"
               }

               override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                   LoginButton.text= response.body()?.tokenType.toString()

               }

           })

            /*.map {
            if(it.isSuccessful){
                print(it.body().toString())
                print("\nsuccess\n")
            }
            else{
                print("\nunsuccess\n")
            }

        }

             */

        LoginButton.setOnClickListener { if (!isPasswordValid(PasswordInput.text!!)) {
            PasswordInput.error="Password is not valid"
        } else {
            // Clear the error.

            val intent = Intent(this,main::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } }
        PasswordInput.setOnKeyListener({ _, _, _ ->
            if (isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = null
            }
            false
        })
    }


    private fun isPasswordValid(text: Editable?): Boolean {
        //return text != null && text.length >= 8
        return true
    }
}


