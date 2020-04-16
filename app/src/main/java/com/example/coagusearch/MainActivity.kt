package com.example.coagusearch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.doctor.doctorMain
import com.example.coagusearch.medicalTeam.MedicalTeamMain
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.appModule
import com.example.coagusearch.patient.main
import kotlinx.android.synthetic.main.loginscreen.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin


class MainActivity : AppCompatActivity() {
    private val authInterceptor: AuthInterceptor by inject()
    private val retrofitClient: RetrofitClient by inject()
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Koin Dependency Injection with modules from [appModule]
        /*

        val bundle: Bundle? = intent.extras
        if(bundle!=null) {
            var editOrNew = bundle!!.getString("type")
            if(editOrNew!!.equals("logout")){
            }
            else{
                startKoin(this, listOf(appModule))
            }
        }
        else{
         */
            startKoin(this, listOf(appModule))
       // }
        // Force init AuthRepository
        AuthRepository(applicationContext, authInterceptor, retrofitClient)
        var authRepository: AuthRepository = get()
        setContentView(R.layout.loginscreen)
        sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor=sharedPreferences!!.edit();
        val checked = sharedPreferences!!.getString("ischecked", "Un Named")
        if(checked.equals("true")){
            val TC = sharedPreferences!!.getString("TC", "")
            val passwords = sharedPreferences!!.getString("passowrd", "")
            authRepository.signIn(TC!!,passwords!!,this)
        }
        LoginButton.setOnClickListener {
            if (!isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = "Password is not valid"
            } else {
                authRepository.signIn("14051222123", "123456", this)
                //directedToMainScreen()

            }
        }
        PasswordInput.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(PasswordInput.text!!)) {
                PasswordInput.error = null
            }
            false
        }
    }

    override fun onBackPressed() {

    }

    fun directedToMainScreen() {
        /*
        if(rememberMeSwitch.isChecked) {
            editor!!.putString("ischecked","true");
            editor!!.putString("TC","14051222123");
            editor!!.putString("passowrd","123456");
            editor!!.commit();
        }
        else{
            editor!!.putString("ischecked","false");
            editor!!.putString("TC","");
            editor!!.putString("passowrd","");
            editor!!.commit();
        }
        */
        val intent = Intent(this, MedicalTeamMain::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        //return text != null && text.length >= 8
        return true
    }
}


