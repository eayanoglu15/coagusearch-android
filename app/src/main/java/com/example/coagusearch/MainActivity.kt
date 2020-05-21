package com.example.coagusearch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.doctor.doctorMain
import com.example.coagusearch.medicalTeam.medicalTeamBottomNavigation
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.patient.UserInfoSingleton
import com.example.coagusearch.patient.main
import kotlinx.android.synthetic.main.loginscreen.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val authInterceptor: AuthInterceptor by inject()
    private val retrofitClient: RetrofitClient by inject()
    var TC:String?=null
    var Pass:String?=null
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)
        var authRepository: AuthRepository = get()
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor = sharedPreferences!!.edit();
        val checked = sharedPreferences!!.getString("ischecked", "Un Named")
        if (checked.equals("true")) {
            val TC = sharedPreferences!!.getString("TC", "")
            val passwords = sharedPreferences!!.getString("passowrd", "")
            authRepository.signIn(TC!!, passwords!!, this)
        }
        LoginButton.setOnClickListener {
            TC=TcInput.text.toString()
            Pass=PasswordInput.text.toString()
            authRepository.signIn(TC!!, Pass!!, this)
        }
    }

    override fun onBackPressed() {

    }

    fun directedToMainScreen() {

        if (rememberMeSwitch.isChecked) {
            editor!!.putString("ischecked", "true");
            editor!!.putString("TC", TC);
            editor!!.putString("passowrd", Pass);
            editor!!.commit();
        } else {
            editor!!.putString("ischecked", "false");
            editor!!.putString("TC", "");
            editor!!.putString("passowrd", "");
            editor!!.commit();
        }
        val userRepository: UsersRepository = get()
        userRepository.getUserInfo(this, 2)

    }

    fun goToActivity() {
        if (UserInfoSingleton.instance.userInfo!!.type.equals("Doctor")) {
            val intent = Intent(this, doctorMain::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (UserInfoSingleton.instance.userInfo!!.type.equals("Patient")) {
            val intent = Intent(this, main::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (UserInfoSingleton.instance.userInfo!!.type.equals("Medical")) {
            val intent = Intent(this, medicalTeamBottomNavigation::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

}


