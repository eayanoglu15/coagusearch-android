package com.example.coagusearch.patient


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.ui.dialog.showProgressLoading
import kotlinx.android.synthetic.main.activity_account_page.*
import org.koin.android.ext.android.get


class accountPage : AppCompatActivity(),
    DateListDialogFragment.BottomSheetListener {
    var userResponse : UserResponse?=null
    var name : String?= null
    var surname : String?= null
    var birthDay : Int?=null
    var birthMonth : Int?=null
    var birthYear : Int?=null
    var height : Double?=null
    var weight : Double?=null
    var bloodType : String?=null
    var rhType : String?=null
    var gender : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_page)
        userResponse= UserInfoSingleton.instance.userInfo
        name=userResponse!!.name
        surname=userResponse!!.surname
        birthDay=userResponse!!.birthDay
        birthMonth=userResponse!!.birthMonth
        birthYear=userResponse!!.birthYear
        height=userResponse!!.height
        weight=userResponse!!.weight
        bloodType=userResponse!!.bloodType
        rhType=userResponse!!.rhType
        gender=userResponse!!.gender
        putCurrentValues()
        saveInfoChanges.setOnClickListener{
            getAndSaveValues()
        }
        backArrow.setOnClickListener{
            this.onBackPressed()
        }


        mainview.setOnTouchListener(OnTouchListener { arg0, arg1 ->
            // action to do
            //accountname.isEnabled=false
            //accountsurname.isEnabled=false
            //accountsurheight.isEnabled=false
            //accountsurweight.isEnabled=false
            accountname.clearFocus()
            accountsurname.clearFocus()
            accountsurweight.clearFocus()
            accountsurheight.clearFocus()
            true //always return true to consume event
        })





    }


    private fun putCurrentValues(){
        if(name!=null) accountname.setText(name.toString().capitalize())
        if(surname!=null)accountsurname.setText(surname.toString().capitalize())
        if(weight!=null) accountsurweight.setText(weight.toString())
        if(height!=null) accountsurheight.setText(height.toString())
        if(birthDay!=null&&birthMonth!=null&&birthYear!=null) textView12.setText(birthDay.toString() + "/" + birthMonth.toString() + "/" + birthYear.toString())
        when(bloodType){
            "A"->blood_radiogroup.check(R.id.radioButton)
            "B"->blood_radiogroup.check(R.id.radioButton2)
            "AB"->blood_radiogroup.check(R.id.radioButton3)
            "O"->blood_radiogroup.check(R.id.radioButton4)
        }
        when(rhType){
            "Positive"->blood_radiogroup2.check(R.id.radioButton6)
            "Negative"->blood_radiogroup2.check(R.id.radioButton5)
        }
        when(gender){
            "Male"->blood_radiogroup3.check(R.id.radioButton7)
            "Female"->blood_radiogroup3.check(R.id.radioButton8)
        }
        textView12.setOnClickListener {
            val bottomSheet =
                DateListDialogFragment()
            bottomSheet.show(supportFragmentManager, "DateListDialogFragment")
        }
    }

    fun getAndSaveValues(){
        name= if (accountname.text.toString().equals("") ||accountname.text.toString().equals("null")) null else accountname.text.toString()
        surname= if (accountsurname.text.toString()=="" ||accountsurname.text.toString()=="null") "" else accountsurname.text.toString()
        weight= if (accountsurweight.text.toString()=="" ||accountsurweight.text.toString()==null) null else accountsurweight.text.toString().toDouble()
        height= if (accountsurheight.text.toString()=="" ||accountsurheight.text.toString()==null) null else accountsurheight.text.toString().toDouble()
        when(blood_radiogroup.checkedRadioButtonId){
            R.id.radioButton ->bloodType="A"
            R.id.radioButton2 ->bloodType="B"
            R.id.radioButton3 ->bloodType="AB"
            R.id.radioButton4 ->bloodType="O"
        }
        when(blood_radiogroup2.checkedRadioButtonId){
            R.id.radioButton6 ->rhType="Positive"
            R.id.radioButton5 ->rhType="Negative"
        }
        when(blood_radiogroup3.checkedRadioButtonId){
            R.id.radioButton7 ->gender="Male"
            R.id.radioButton8 ->gender="Female"
        }
        val userRepository: UsersRepository = get()
        userRepository.saveBodyInfo(UserBodyInfoSaveRequest(name,surname,birthDay,birthMonth,birthYear,height,weight,bloodType,rhType,gender),this)
    }

    fun saved(){
        val userRepository: UsersRepository = get()
        showProgressLoading(true,this)
        userRepository.getUserInfo(this,1)
        this.onBackPressed();
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
    override fun onButtonClicked(birthDay:Int,birthMonth:Int,birthYear:Int) {
        this.birthDay=birthDay
        this.birthMonth=birthMonth
        this.birthYear=birthYear
        textView12.text=birthDay.toString()+"/"+birthMonth.toString()+"/"+birthYear.toString()
    }
    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}
