package com.example.coagusearch.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coagusearch.R
import kotlinx.android.synthetic.main.activity_decision.*

class decisionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decision)


        decidetreatmentcard.setOnClickListener {
            val intent = Intent(this, decideTreatment::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        treatmentstatuscard.setOnClickListener {
            val intent = Intent(this, treatmentStatus::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
