package com.example.coagusearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.main.*
class main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        loadFragment(mainmenu(),0)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu-> {
                    loadFragment(mainmenu(),0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.calendar-> {
                    loadFragment(appointmentspage(),0)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.person-> {
                    loadFragment(personpage(),0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
    }
    private fun loadFragment(fragment: Fragment,int: Int) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        if(int!=0) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
