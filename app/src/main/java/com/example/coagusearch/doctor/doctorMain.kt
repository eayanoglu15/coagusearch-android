package com.example.coagusearch.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.coagusearch.R
import kotlinx.android.synthetic.main.main.*

class doctorMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_main)
        loadFragment(doctorHomeFragment(),0)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu-> {
                    loadFragment(doctorHomeFragment(),0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.notification-> {
                    loadFragment(doctorNotificationFragment(),0)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.patiens-> {
                    loadFragment(doctorPatientsFragment(),0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bloodbank-> {
                    loadFragment(doctorBloodBankFragment(),0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {

    }
    private fun loadFragment(fragment: Fragment, int: Int) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        if(int!=0) {
            transaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
