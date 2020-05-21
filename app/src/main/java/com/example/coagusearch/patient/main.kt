package com.example.coagusearch.patient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coagusearch.R
import com.example.coagusearch.network.RegularMedication.model.RegularMedicationRepository
import com.example.coagusearch.ui.dialog.showProgressLoading
import kotlinx.android.synthetic.main.main.*
import org.koin.android.ext.android.get
import java.util.*
import kotlin.concurrent.schedule

class main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        //loadFragment(mainmenu(),0)

        showProgressLoading(true, this)
        val medRepository: RegularMedicationRepository = get()
        medRepository.getUsersDrug(this)

        bottom_navigation.setOnNavigationItemReselectedListener { }
        when (bottom_navigation.selectedItemId) {
            R.id.menu -> {
                loadFragment(mainmenu(), 0)
                //return@setOnNavigationItemSelectedListener true
            }
            R.id.calendar -> {
                loadFragment(appointmentspage(), 0)
                //return@setOnNavigationItemSelectedListener true
            }

            R.id.person -> {
                loadFragment(personpage(), 0)
                //return@setOnNavigationItemSelectedListener true
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> {
                    loadFragment(mainmenu(), 0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.calendar -> {
                    loadFragment(appointmentspage(), 0)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.person -> {
                    loadFragment(personpage(), 0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    public override fun onRestart() {
        super.onRestart()
        Timer().schedule(500) {
            when (bottom_navigation.selectedItemId) {
                R.id.menu -> {
                    loadFragment(mainmenu(), 0)
                    //return@setOnNavigationItemSelectedListener true
                }
                R.id.calendar -> {
                    loadFragment(appointmentspage(), 0)
                    //return@setOnNavigationItemSelectedListener true
                }

                R.id.person -> {
                    loadFragment(personpage(), 0)
                    //return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }

    override fun onBackPressed() {}
    fun loadFragment(fragment: Fragment, int: Int) {
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
