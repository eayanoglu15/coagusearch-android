package com.example.coagusearch.medicalTeam

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.R
import com.example.coagusearch.doctor.intentFailDialog
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.PatientBodyInfoSaveRequest
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.Users.response.UserResponse
import kotlinx.android.synthetic.main.activity_med_team_edit_patient.*
import org.koin.android.ext.android.get

class MedTeamEditPatient : AppCompatActivity(),DateBottomSheet.BottomSheetListener {
    var patient:UserResponse?=null
    var userInfo:UserResponse?=null
    var name: String? = null
    var surname: String? = null
    var birthDay: Int? = null
    var birthMonth: Int? = null
    var birthYear: Int? = null
    var height: Double? = null
    var weight: Double? = null
    var bloodType: String? = null
    var rhType: String? = null
    var gender: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_team_edit_patient)

        if (intent.hasExtra("patient")) {
            patient =
                intent.getSerializableExtra("patient") as? UserResponse

                getData()
        }
        else{
            intentFailDialog(this)
        }

    }
    private fun getData(){
        val userRepository: UsersRepository = get()
        userRepository.getPatientDetail(this, PatientDetailRequest(patient!!.userId!!))
    }

    fun setData(detail: PatientDetailResponse){
        userInfo=detail.patientResponse
            if (userInfo!!.name != null) accountname.setText(userInfo!!.name.toString().capitalize())
            if (userInfo!!.surname != null) accountsurname.setText(userInfo!!.surname.toString().capitalize())
            if (userInfo!!.weight != null) accountsurweight.setText(userInfo!!.weight.toString())
            if (userInfo!!.height != null) accountsurheight.setText(userInfo!!.height.toString())
            if (userInfo!!.birthDay != null && userInfo!!.birthMonth != null && userInfo!!.birthYear != null) textView12.setText(userInfo!!.birthDay.toString() + "/" + userInfo!!.birthMonth.toString() + "/" + userInfo!!.birthYear.toString())
            when (userInfo!!.bloodType) {
                "A" -> blood_radiogroup.check(R.id.radioButton)
                "B" -> blood_radiogroup.check(R.id.radioButton2)
                "AB" -> blood_radiogroup.check(R.id.radioButton3)
                "O" -> blood_radiogroup.check(R.id.radioButton4)
            }
            when (userInfo!!.rhType) {
                "Positive" -> blood_radiogroup2.check(R.id.radioButton6)
                "Negative" -> blood_radiogroup2.check(R.id.radioButton5)
            }
            when (userInfo!!.gender) {
                "Male" -> blood_radiogroup3.check(R.id.radioButton7)
                "Female" -> blood_radiogroup3.check(R.id.radioButton8)
            }

        name=userInfo!!.name
        surname=userInfo!!.surname
        weight=userInfo!!.weight
        height=userInfo!!.height
        bloodType=userInfo!!.bloodType
        rhType=userInfo!!.rhType
        birthDay=userInfo!!.birthDay
        birthMonth=userInfo!!.birthMonth
        birthYear=userInfo!!.birthYear
        gender=userInfo!!.gender
        textView12.setOnClickListener {
            val bottomSheet =
                DateBottomSheet()
            bottomSheet.show(supportFragmentManager, "DateBottomSheet")
        }

        saveInfoChanges.setOnClickListener {
            getAndSaveValues()
        }
    }
    private  fun getAndSaveValues(){
        name =
            if (accountname.text.toString().equals("") || accountname.text.toString().equals("null")) null else accountname.text.toString()
        surname =
            if (accountsurname.text.toString() == "" || accountsurname.text.toString() == "null") null else accountsurname.text.toString()
        weight =
            if (accountsurweight.text.toString() == "" || accountsurweight.text.toString() == null) null else accountsurweight.text.toString().toDouble()
        height =
            if (accountsurheight.text.toString() == "" || accountsurheight.text.toString() == null) null else accountsurheight.text.toString().toDouble()
        when (blood_radiogroup.checkedRadioButtonId) {
            R.id.radioButton -> bloodType = "A"
            R.id.radioButton2 -> bloodType = "B"
            R.id.radioButton3 -> bloodType = "AB"
            R.id.radioButton4 -> bloodType = "O"
        }
        when (blood_radiogroup2.checkedRadioButtonId) {
            R.id.radioButton6 -> rhType = "Positive"
            R.id.radioButton5 -> rhType = "Negative"
        }
        when (blood_radiogroup3.checkedRadioButtonId) {
            R.id.radioButton7 -> gender = "Male"
            R.id.radioButton8 -> gender = "Female"
        }

        if(name!=null&&surname!=null) {
            val userRepository: UsersRepository = get()
            userRepository.saveBodyInfoOfPatient(
                PatientBodyInfoSaveRequest(
                    name,
                    surname,
                    birthDay,
                    birthMonth,
                    birthYear,
                    height,
                    weight,
                    bloodType,
                    rhType,
                    gender,
                    userInfo!!.userId
                ), this
            )
        }
        else{
            Toast.makeText(this,getString(R.string.nameerror), Toast.LENGTH_LONG).show()
        }
    }

    override fun onButtonClicked(birthDay: Int, birthMonth: Int, birthYear: Int) {
        this.birthDay = birthDay
        this.birthMonth = birthMonth
        this.birthYear = birthYear
        textView12.text =
            birthDay.toString() + "/" + birthMonth.toString() + "/" + birthYear.toString()
    }
}
