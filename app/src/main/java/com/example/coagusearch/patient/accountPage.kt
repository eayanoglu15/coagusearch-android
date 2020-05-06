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
    var userResponse: UserResponse? = null
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
        setContentView(R.layout.activity_account_page)
        userResponse = UserInfoSingleton.instance.userInfo
        name = userResponse!!.name
        surname = userResponse!!.surname
        birthDay = userResponse!!.birthDay
        birthMonth = userResponse!!.birthMonth
        birthYear = userResponse!!.birthYear
        height = userResponse!!.height
        weight = userResponse!!.weight
        bloodType = userResponse!!.bloodType
        rhType = userResponse!!.rhType
        gender = userResponse!!.gender
        putCurrentValues()
        getAndSaveValues()
        saveInfoChanges.setOnClickListener {
            getAndSaveValues()
            val userRepository: UsersRepository = get()
            userRepository.saveBodyInfo(
                UserBodyInfoSaveRequest(
                    name,
                    surname,
                    birthDay,
                    birthMonth,
                    birthYear,
                    height,
                    weight,
                    bloodType,
                    rhType,
                    gender
                ), this
            )
        }
        backArrow.setOnClickListener {
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


    private fun putCurrentValues() {
        if (name != null) accountname.setText(name.toString().capitalize())
        if (surname != null) accountsurname.setText(surname.toString().capitalize())
        if (weight != null) accountsurweight.setText(weight.toString())
        if (height != null) accountsurheight.setText(height.toString())
        if (birthDay != null && birthMonth != null && birthYear != null) textView12.setText(birthDay.toString() + "/" + birthMonth.toString() + "/" + birthYear.toString())
        when (bloodType) {
            "A" -> {bloodAButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                bloodAButton.setTextColor(resources.getColor(R.color.white))}
            "B" -> {bloodBButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                bloodBButton.setTextColor(resources.getColor(R.color.white))}
            "AB" -> {bloodABButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                bloodABButton.setTextColor(resources.getColor(R.color.white))}
            "O" -> {blood0Button.setBackgroundResource(R.drawable.selectedbuttonbackground)
                blood0Button.setTextColor(resources.getColor(R.color.white))}
        }
        when (rhType) {
            "Positive" -> {positiveButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                positiveButton.setTextColor(resources.getColor(R.color.white))}

            "Negative" -> {negativeButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                negativeButton.setTextColor(resources.getColor(R.color.white))}
        }
        when (gender) {
            "Male" -> {maleButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                maleButton.setTextColor(resources.getColor(R.color.white))}
            "Female" -> {femaleButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
                femaleButton.setTextColor(resources.getColor(R.color.white))}
        }
        textView12.setOnClickListener {
            val bottomSheet =
                DateListDialogFragment()
            bottomSheet.show(supportFragmentManager, "DateListDialogFragment")
        }
    }

    fun getAndSaveValues() {
        name =
            if (accountname.text.toString().equals("") || accountname.text.toString().equals("null")) null else accountname.text.toString()
        surname =
            if (accountsurname.text.toString() == "" || accountsurname.text.toString() == "null") "" else accountsurname.text.toString()
        weight =
            if (accountsurweight.text.toString() == "" || accountsurweight.text.toString() == null) null else accountsurweight.text.toString().toDouble()
        height =
            if (accountsurheight.text.toString() == "" || accountsurheight.text.toString() == null) null else accountsurheight.text.toString().toDouble()

        bloodAButton.setOnClickListener(){
            bloodType = "A"
            bloodAButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            bloodAButton.setTextColor(resources.getColor(R.color.white))
            bloodBButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodBButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            bloodABButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodABButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            blood0Button.setBackgroundResource(R.drawable.selectbuttonbackground)
            blood0Button.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        bloodBButton.setOnClickListener(){
            bloodType = "B"
            bloodBButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            bloodBButton.setTextColor(resources.getColor(R.color.white))
            bloodAButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodAButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            bloodABButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodABButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            blood0Button.setBackgroundResource(R.drawable.selectbuttonbackground)
            blood0Button.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        bloodABButton.setOnClickListener(){
            bloodType = "AB"
            bloodABButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            bloodABButton.setTextColor(resources.getColor(R.color.white))
            bloodBButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodBButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            bloodAButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodAButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            blood0Button.setBackgroundResource(R.drawable.selectbuttonbackground)
            blood0Button.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        blood0Button.setOnClickListener(){
            bloodType = "0"
            blood0Button.setBackgroundResource(R.drawable.selectedbuttonbackground)
            blood0Button.setTextColor(resources.getColor(R.color.white))
            bloodBButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodBButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            bloodABButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodABButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            bloodAButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            bloodAButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        positiveButton.setOnClickListener(){
            rhType = "Positive"
            positiveButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            positiveButton.setTextColor(resources.getColor(R.color.white))
            negativeButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            negativeButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
        negativeButton.setOnClickListener(){
            rhType = "Negative"
            negativeButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            negativeButton.setTextColor(resources.getColor(R.color.white))
            positiveButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            positiveButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        maleButton.setOnClickListener(){
            gender = "Male"
            maleButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            maleButton.setTextColor(resources.getColor(R.color.white))
            femaleButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            femaleButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        femaleButton.setOnClickListener(){
            gender = "Female"
            femaleButton.setBackgroundResource(R.drawable.selectedbuttonbackground)
            femaleButton.setTextColor(resources.getColor(R.color.white))
            maleButton.setBackgroundResource(R.drawable.selectbuttonbackground)
            maleButton.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

    /*    val userRepository: UsersRepository = get()
        userRepository.saveBodyInfo(
            UserBodyInfoSaveRequest(
                name,
                surname,
                birthDay,
                birthMonth,
                birthYear,
                height,
                weight,
                bloodType,
                rhType,
                gender
            ), this
        )*/
    }

    fun saved() {
        val userRepository: UsersRepository = get()
        showProgressLoading(true, this)
        userRepository.getUserInfo(this, 1)
        this.onBackPressed();
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    override fun onButtonClicked(birthDay: Int, birthMonth: Int, birthYear: Int) {
        this.birthDay = birthDay
        this.birthMonth = birthMonth
        this.birthYear = birthYear
        textView12.text =
            birthDay.toString() + "/" + birthMonth.toString() + "/" + birthYear.toString()
    }

    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}
