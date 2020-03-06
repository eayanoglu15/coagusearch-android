package com.example.coagusearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coagusearch.Class.AccountInfo
import kotlinx.android.synthetic.main.activity_account_page.*

class accountPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_page)

            accountname.setText("Ökkeş Uğur")
            accountsurname.setText("ULAŞ")
            accountsurage.setText("22")
            accountsurheight.setText("175")
            accountsurweight.setText("75")

    }




}
