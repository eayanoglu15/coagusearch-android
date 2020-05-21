package com.example.coagusearch.medicalTeam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Auth.request.UserSaveRequest
import com.example.coagusearch.network.Auth.response.UserSaveResponse
import kotlinx.android.synthetic.main.activity_med_team_add_patient.*
import kotlinx.android.synthetic.main.activity_med_team_add_patient.saveInfoChanges
import kotlinx.android.synthetic.main.activity_med_team_add_patient.textView12
import org.koin.android.ext.android.get


class MedTeamAddPatient : AppCompatActivity(),DateBottomSheet.BottomSheetListener {

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
    var TC:Long?=null
    var ID:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_team_add_patient)


        if (intent.hasExtra("patientID")) {
            val bundle: Bundle? = intent.extras
            TC = bundle!!.getLong("patientID")
            patientID.setText(TC!!.toInt().toString())
            ID=TC.toString()
        }
        else{

        }

        saveInfoChanges.setOnClickListener{
            getAndSaveValues()
        }
        textView12.setOnClickListener {
            val bottomSheet =
                DateBottomSheet()
            bottomSheet.show(supportFragmentManager, "DateBottomSheet")
        }
    }


    override fun onButtonClicked(birthDay: Int, birthMonth: Int, birthYear: Int) {
        this.birthDay = birthDay
        this.birthMonth = birthMonth
        this.birthYear = birthYear
        textView12.text =
            birthDay.toString() + "/" + birthMonth.toString() + "/" + birthYear.toString()
    }

    fun showPass(response:UserSaveResponse){
        val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle)
        builder.setMessage(getString(R.string.passMedical)+response.protocolCode)
        builder.setPositiveButton("OK") { dialog, which ->
            onBackPressed()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun getAndSaveValues(){
        ID=
            if (patientID.text.toString() == "" || patientID.text.toString() == null) null else patientID.text.toString()
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

        if(name!=null&&surname!=null&&ID!=null) {
            val Repository: AuthRepository = get()
            Repository.saveBodyInfoOfPatient(
                UserSaveRequest(
                    ID!!,
                    "Patient",
                    name!!,
                    surname,
                    birthDay,
                    birthMonth,
                    birthYear,
                    height,
                    weight,
                    bloodType,
                    rhType,
                    gender,
                    null
                ), this
            )
        }
        else{
            Toast.makeText(this,getString(R.string.nameerrorID), Toast.LENGTH_LONG).show()
        }

    }
}
