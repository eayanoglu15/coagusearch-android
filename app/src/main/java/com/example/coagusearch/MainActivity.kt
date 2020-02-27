package com.example.coagusearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.loginscreen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)
        LoginButton.setOnClickListener { if (!isPasswordValid(PasswordInput.text!!)) {
            PasswordInput.error="Password is not valid"
        } else {
            // Clear the error.
            PasswordInput.error = null
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
