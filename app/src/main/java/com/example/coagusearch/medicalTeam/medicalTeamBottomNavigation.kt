package com.example.coagusearch.medicalTeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.coagusearch.R
import com.example.coagusearch.doctor.*
import com.example.coagusearch.network.Users.model.UsersRepository
import kotlinx.android.synthetic.main.main.*
import org.koin.android.ext.android.get

class medicalTeamBottomNavigation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_team_bottom_navigation)
        loadFragment(medicalHomeFragment(), 0)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> {
                    loadFragment(medicalHomeFragment(), 0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.notification -> {
                    loadFragment(MedicalPrepareFragment(), 0)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.patiens -> {
                    loadFragment(medicalTeamPatientsFragment(), 0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(medicalProfileFragment(), 0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        val userRepository: UsersRepository = get()
        userRepository.getUserInfo(this, 1)
    }

    override fun onBackPressed() {

    }

    private fun loadFragment(fragment: Fragment, int: Int) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        if (int != 0) {
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

