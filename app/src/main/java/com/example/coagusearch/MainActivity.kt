package com.example.coagusearch

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.appModule
import com.example.coagusearch.ui.dialog.LoadingDialog
import com.example.coagusearch.ui.dialog.LoadingProgressDialog
import com.example.coagusearch.ui.dialog.LoadingProgressSingleton
import kotlinx.android.synthetic.main.loading_lottie_dialog.*
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

        LoginButton.setOnClickListener {
            if (!isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = "Password is not valid"
            } else {
                showProgressLoading(true)
                authRepository.signIn("345678754", "123456", this)
            }
        }
        PasswordInput.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = null
            }
            false
        }
    }
    fun showProgressLoading(loading: Boolean) {
        if (loading) {
            if (LoadingProgressSingleton.dialog == null) {
                createLoadingDialog(true)
            }
            LoadingProgressSingleton.dialog?.show()
        } else {
            LoadingProgressSingleton.dialog?.dismiss()
            LoadingProgressSingleton.dialog = null
        }
    }
    private fun createLoadingDialog(isProgressDialog: Boolean = false) {

        fun getDialog(): Dialog {
            return if (isProgressDialog) {
                LoadingProgressDialog(this).create()
            } else {
                LoadingDialog(this).create()
            }
        }

        LoadingProgressSingleton.dialog = getDialog()
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


