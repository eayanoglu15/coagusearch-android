package com.example.coagusearch

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.network.RegularMedication.model.RegularMedicationRepository
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.ui.dialog.LoadingDialog
import com.example.coagusearch.ui.dialog.LoadingProgressDialog
import com.example.coagusearch.ui.dialog.LoadingProgressSingleton
import com.example.coagusearch.ui.dialog.showProgressLoading
import kotlinx.android.synthetic.main.activity_account_page.*
import kotlinx.android.synthetic.main.activity_add_medicine.view.*
import org.koin.android.ext.android.get


class accountPage : AppCompatActivity(),DateListDialogFragment.BottomSheetListener{
    var userResponse: UserResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_page)
        userResponse=UserInfoSingleton.instance.userInfo
        accountname.setText(userResponse!!.name)
        accountsurname.setText(userResponse!!.surname)
        accountsurheight.setText(userResponse!!.height.toString())
        accountsurweight.setText(userResponse!!.weight.toString())
        textView12.setText(""+userResponse!!.dateOfBirth)
        var bloodType:String?=userResponse!!.bloodType
        when(bloodType){
            "A"->blood_radiogroup.check(R.id.radioButton)
            "B"->blood_radiogroup.check(R.id.radioButton2)
            "AB"->blood_radiogroup.check(R.id.radioButton3)
            "O"->blood_radiogroup.check(R.id.radioButton4)
        }
        var rhType:String?=userResponse!!.rhType
        when(rhType){
            "Positive"->blood_radiogroup2.check(R.id.radioButton6)
             "Negative"->blood_radiogroup2.check(R.id.radioButton5)
        }
        var gender:String?=userResponse!!.gender
        when(gender){
            "Male"->blood_radiogroup3.check(R.id.radioButton7)
            "Female"->blood_radiogroup3.check(R.id.radioButton8)
        }
        textView12.setOnClickListener {
            val bottomSheet = DateListDialogFragment()
            bottomSheet.show(supportFragmentManager, "DateListDialogFragment")
        }
        saveInfoChanges.setOnClickListener{
            getAndSaveValues()
        }
    }
    fun getAndSaveValues(){
        var name:String?=accountname.text.toString()
        var surname:String?=accountsurname.text.toString()
        var height:Double?=accountsurheight.text.toString().toDouble()
        var weight:Double?=accountsurweight.text.toString().toDouble()
        var birthDate:String?=textView12.text.toString()
        var bloodType:String?=null
        when(blood_radiogroup.checkedRadioButtonId){
            R.id.radioButton->bloodType="A"
            R.id.radioButton2->bloodType="B"
            R.id.radioButton3->bloodType="AB"
            R.id.radioButton4->bloodType="O"
        }
        var rhType:String?=null
        when(blood_radiogroup2.checkedRadioButtonId){
            R.id.radioButton6->rhType="Positive"
            R.id.radioButton5->rhType="Negative"
        }
        var gender:String?= null
        when(blood_radiogroup3.checkedRadioButtonId){
            R.id.radioButton7->gender="Male"
            R.id.radioButton8->gender="Female"
        }
        val userRepository: UsersRepository = get()
        userRepository.saveBodyInfo(UserBodyInfoSaveRequest(name,surname,birthDate,height,weight,bloodType,rhType,gender),this)
    }
    fun saved(){
        val userRepository: UsersRepository = get()
        showProgressLoading(true,this)
        userRepository.getUserInfo(this,1)
        Toast.makeText(this,"Bilgileriniz Kaydedildi",Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }

    override fun onButtonClicked(text: String?) {
        textView12.text=text
    }






}
