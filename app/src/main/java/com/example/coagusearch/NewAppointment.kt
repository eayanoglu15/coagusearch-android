package com.example.coagusearch

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coagusearch.network.Appointment.model.AppointmentRepository
import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.ui.dialog.LoadingDialog
import com.example.coagusearch.ui.dialog.LoadingProgressDialog
import com.example.coagusearch.ui.dialog.LoadingProgressSingleton
import kotlinx.android.synthetic.main.activity_add_medicine.*
import kotlinx.android.synthetic.main.activity_new_appointment.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class NewAppointment : AppCompatActivity() {
    private val authInterceptor: AuthInterceptor by inject()
    private val retrofitClient: RetrofitClient by inject()

    private var doctorPickerIsON:Boolean=false
    private var datePickerIsON:Boolean=false
    private var slotPickerIsON:Boolean=false
    var appointmentResponse: WeeklyAvailabilityResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)
        timeSlotPicker.visibility=View.GONE
        appointmentDatePicker.visibility=View.GONE
        doctorPicker.visibility=View.GONE
        setListenersNewAppointment()
        showProgressLoading(true)

        AppointmentRepository(this,retrofitClient)
        val appointmentRepository: AppointmentRepository = get()
        appointmentResponse=appointmentRepository.availableTimeSlotsForAppointment()
        println("Hello"+appointmentResponse.toString())
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

    private fun setListenersNewAppointment(){
        doctorPicker.setOnClickListener {
            if(doctorPickerIsON) {
                doctorPicker.visibility = View.GONE
                doctorPickerIsON=false
                ocYourDoctor.setImageResource(R.drawable.downarrow)
            }
        }
        ocYourDoctor.setOnClickListener{
            if(doctorPickerIsON) {
                doctorPicker.visibility = View.GONE
                doctorPickerIsON=false
                ocYourDoctor.setImageResource(R.drawable.downarrow)
            }
            else{
                doctorPicker.visibility = View.VISIBLE
                doctorPickerIsON=true
                ocYourDoctor.setImageResource(R.drawable.uparrow)
            }
        }
        appointmentDatePicker.setOnClickListener {
            if(datePickerIsON) {
                appointmentDatePicker.visibility = View.GONE
                datePickerIsON=false
                ocAppointmentDate.setImageResource(R.drawable.downarrow)
            }
        }
        ocAppointmentDate.setOnClickListener{
            if(datePickerIsON) {
                appointmentDatePicker.visibility = View.GONE
                datePickerIsON=false
                ocAppointmentDate.setImageResource(R.drawable.downarrow)
            }
            else{
                appointmentDatePicker.visibility = View.VISIBLE
                datePickerIsON=true
                ocAppointmentDate.setImageResource(R.drawable.uparrow)
            }
        }
        timeSlotPicker.setOnClickListener {
            if(slotPickerIsON) {
                timeSlotPicker.visibility = View.GONE
                slotPickerIsON=false
                ocTimeSlot.setImageResource(R.drawable.downarrow)
            }
        }
        ocTimeSlot.setOnClickListener{
            if(slotPickerIsON) {
                timeSlotPicker.visibility = View.GONE
                slotPickerIsON=false
                ocTimeSlot.setImageResource(R.drawable.downarrow)
            }
            else{
                timeSlotPicker.visibility = View.VISIBLE
                slotPickerIsON=true
                ocTimeSlot.setImageResource(R.drawable.uparrow)
            }
        }
    }


}
