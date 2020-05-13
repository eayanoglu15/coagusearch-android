package com.example.coagusearch.medicalTeam

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.MainActivity

import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.patient.UserInfoSingleton
import com.example.coagusearch.patient.accountPage
import com.example.coagusearch.patient.main
import kotlinx.android.synthetic.main.fragment_medical_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [medicalProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class medicalProfileFragment : Fragment() {

    var userResponse: UserResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =      inflater.inflate(R.layout.fragment_medical_profile, container, false)
        userResponse = UserInfoSingleton.instance.userInfo
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileFragment_name.text = userResponse!!.name + " " + userResponse!!.surname
        val birthdate = getString(R.string.birthdate)
        if (userResponse!!.birthDay != null && userResponse!!.birthMonth != null && userResponse!!.birthYear != null)
            profileFragment_Age.text =
                userResponse!!.birthDay.toString() + "/" + userResponse!!.birthMonth.toString() + "/" + userResponse!!.birthYear.toString()
        else
            profileFragment_Age.text = "-/-/-"
        val height = getString(R.string.height)
        val weight = getString(R.string.weight)
        if (userResponse!!.height != null) {
            profileFragment_height.text = height + ":" + userResponse!!.height.toString()
        } else {
            profileFragment_height.text = height + ":-"
        }
        if (userResponse!!.weight != null) {
            profileFragment_weight.text = weight + ":" + userResponse!!.weight.toString()
        } else {
            profileFragment_weight.text = weight + ":-"
        }
        switchToPatient.setOnClickListener {
            val intent = Intent(getActivity(), main::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        editProfile.setOnClickListener {
            val intent = Intent(getActivity(), accountPage::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

        }
    }


}
