package com.example.coagusearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.coagusearch.api.AuthApi
import com.example.coagusearch.entity.Auth
import com.example.coagusearch.entity.LoginRequest
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

            /*
        call.enqueue(object:retrofit2.Callback<Auth>{
            override fun onFailure(call: Call<Auth>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                // Toast.makeText(applicationContext,"Not ",Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //Toast.makeText(applicationContext,"DOne",Toast.LENGTH_LONG).show()
            }
        })
             */
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
