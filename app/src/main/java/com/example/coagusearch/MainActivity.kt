package com.example.coagusearch

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.appModule
import com.example.coagusearch.ui.dialog.showProgressLoading
import kotlinx.android.synthetic.main.loginscreen.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin


class MainActivity : AppCompatActivity() {
    private val authInterceptor: AuthInterceptor by inject()
    private val retrofitClient: RetrofitClient by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Koin Dependency Injection with modules from [appModule]
        startKoin(this, listOf(appModule))
        // Force init AuthRepository
        AuthRepository(applicationContext, authInterceptor, retrofitClient)
        var authRepository: AuthRepository = get()
        setContentView(R.layout.loginscreen)
        PasswordInput.setText("123456")
        TcInput.setText("14051234123")
        LoginButton.setOnClickListener {
            if (!isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = "Password is not valid"
            } else {
                authRepository.signIn("14051222123", "123456", this)
            }
        }
        PasswordInput.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = null
            }
            false
        }
    }

    fun directedToMainScreen() {
        val intent = Intent(this, main::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        //return text != null && text.length >= 8
        return true
    }
}


