package com.example.coagusearch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.shared.RetrofitClient
import kotlinx.android.synthetic.main.loginscreen.*
import okhttp3.OkHttpClient
import okhttp3.Protocol


class MainActivity: AppCompatActivity(){
    private val authRepository: AuthRepository = AuthRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)
        val okHttpClientBuilder = OkHttpClient.Builder()
        print("\n\n\n\nugur ulas\n\n\n\n")

        val response = authRepository.signIn("14051222123","123456")
        println(response.toString())
        textView2.text = response?.tokenType


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


