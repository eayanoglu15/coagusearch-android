package com.example.coagusearch.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.notifications.model.NotificationRepository
import com.example.coagusearch.network.notifications.request.CallPatientRequest
import com.example.coagusearch.network.notifications.request.NotifyMedicalRequest
import kotlinx.android.synthetic.main.activity_decision.*
import org.koin.android.ext.android.get

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
                    var nextAppointment = patientInfo!!.userAppointmentResponse.nextAppointment
                    if (nextAppointment == null) {
                        callForAppointmentard.setOnClickListener {
                            val notificationRepository: NotificationRepository = get()
                            notificationRepository.callPatient(CallPatientRequest(patientid!!),this)
                        }
                    } else {
                        callForAppointmentard.visibility = View.GONE
                    }
                    notifmedcard.setOnClickListener {
                        val notificationRepository: NotificationRepository = get()
                        notificationRepository.notifyMedical(NotifyMedicalRequest(patientid!!),this)
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
