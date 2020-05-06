package com.example.coagusearch.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import kotlinx.android.synthetic.main.activity_decision.*

class decisionActivity : AppCompatActivity() {
    var bloodTestId:Long?=null
    var patientInfo: PatientDetailResponse? = null
    var patientid:Long?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decision)

        if (intent.hasExtra("id")) {
            val bundle: Bundle? = intent.extras
            patientid = bundle!!.getLong("id")

            if (intent.hasExtra("bloodTestId")) {
                val bundle: Bundle? = intent.extras
                bloodTestId = bundle!!.getLong("bloodTestId")
                if (intent.hasExtra("PatientDetailResponse")) {
                    patientInfo =
                        intent.getSerializableExtra("PatientDetailResponse") as? PatientDetailResponse
                    decidetreatmentcard.setOnClickListener {
                        val intent = Intent(this, decideTreatment::class.java)
                        intent.putExtra("PatientDetailResponse", patientInfo)
                        intent.putExtra("bloodTestId", bloodTestId!!)
                        intent.putExtra("id", patientid!!)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                    treatmentstatuscard.setOnClickListener {
                        val intent = Intent(this, treatmentStatus::class.java)
                        intent.putExtra("bloodTestId", bloodTestId!!)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                } else {
                    intentFailDialog(this)
                }

            } else {
                intentFailDialog(this)
            }
        }
        else {
            intentFailDialog(this)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
