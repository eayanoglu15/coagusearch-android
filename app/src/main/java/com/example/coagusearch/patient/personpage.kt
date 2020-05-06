package com.example.coagusearch.patient


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.MainActivity
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorMain
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.patient.PatientAdapters.MedicineAdapterProfileFragment
import kotlinx.android.synthetic.main.activity_account_page.*
import kotlinx.android.synthetic.main.fragment_doctor_profile.*
import kotlinx.android.synthetic.main.fragment_personpage.*
import kotlinx.android.synthetic.main.fragment_personpage.logouticon
import kotlinx.android.synthetic.main.fragment_personpage.profileFragment_Age
import kotlinx.android.synthetic.main.fragment_personpage.profileFragment_height
import kotlinx.android.synthetic.main.fragment_personpage.profileFragment_name
import kotlinx.android.synthetic.main.fragment_personpage.profileFragment_weight

class personpage : Fragment() {
    var medicines = ArrayList<MedicineInfoResponse>()
    var adapter: MedicineAdapterProfileFragment? = null
    var userResponse: UserResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_personpage, container, false)
        userResponse = UserInfoSingleton.instance.userInfo
        if (UserInfoSingleton.instance.medInfo!!.userDrugs != null) {
            medicines = ArrayList(UserInfoSingleton.instance.medInfo!!.userDrugs)
        }
        var myticketlist: ListView
        adapter = MedicineAdapterProfileFragment(
            this.context!!,
            medicines
        )
        myticketlist = view.findViewById<ListView>(R.id.medlist)
        myticketlist.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editbutton.setOnClickListener {
            val intent = Intent(getActivity(), accountPage::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        profileNewMed.setOnClickListener {
            val intent = Intent(getActivity(), AddMedicine::class.java)
            intent.putExtra("type", "new")
            startActivity(intent)
            getActivity()?.overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
        logouticon.setOnClickListener {
            var sharedPreferences: SharedPreferences? = null
            var editor: SharedPreferences.Editor? = null
            sharedPreferences =
                activity!!.getSharedPreferences("LoginPrefs", AppCompatActivity.MODE_PRIVATE)
            editor = sharedPreferences!!.edit();
            editor!!.putString("ischecked", "false");
            editor!!.putString("TC", "");
            editor!!.putString("passowrd", "");
            editor!!.commit();
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
        if (userResponse!!.type.equals("Doctor")) {
            button2.setOnClickListener {
                val intent = Intent(getActivity(), doctorMain::class.java)
                startActivity(intent)
            }
        } else {
            button2.visibility = View.GONE

        }
        profileFragment_name.text = userResponse!!.name + " " + userResponse!!.surname
        val birthdate = getString(R.string.birthdate)
        if (userResponse!!.birthDay != null && userResponse!!.birthMonth != null && userResponse!!.birthYear != null)
            profileFragment_Age.text = "Birthdate: " +
                userResponse!!.birthDay.toString() + "/" + userResponse!!.birthMonth.toString() + "/" + userResponse!!.birthYear.toString()
        else
            profileFragment_Age.text = "-/-/-"
        val height = getString(R.string.height)
        val weight = getString(R.string.weight)
        if (userResponse!!.height != null) {
            profileFragment_height.text = "Height: " + userResponse!!.height.toString() + " cm"
        } else {
            profileFragment_height.text = "Height" + ": -"
        }
        if (userResponse!!.weight != null) {
            profileFragment_weight.text = "Weight: " + userResponse!!.weight.toString() + " kg"
        } else {
            profileFragment_weight.text = "Weight" + ": -"
        }

        var rhStatus = "null"

        if(userResponse!!.bloodType != null && userResponse!!.rhType != null){
            if(userResponse!!.rhType == "Positive") rhStatus = "+"
            if(userResponse!!.rhType == "Negative") rhStatus = "-"
            profileFragment_blood.text = "Blood Type: " + userResponse!!.bloodType + " Rh" + rhStatus
        } else{
            profileFragment_blood.text = "Blood Type" + ": -"
        }
    }


}
