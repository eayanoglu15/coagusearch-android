package com.example.coagusearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_account_page.*


class accountPage : AppCompatActivity(),DateListDialogFragment.BottomSheetListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_page)
            /*
            accountname.setText("Ökkeş Uğur")
            accountsurname.setText("ULAŞ")
            accountsurage.setText("22")
            accountsurheight.setText("175")
            accountsurweight.setText("75")
             */
            blood_radiogroup.setOnCheckedChangeListener{group,checkedid->
                when(checkedid){
                    R.id.radioButton->{

                    }
                    R.id.radioButton2->{

                    }
                    R.id.radioButton3->{
                    }
                }
            }
        textView12.setOnClickListener {
            val bottomSheet = DateListDialogFragment()
            bottomSheet.show(supportFragmentManager, "DateListDialogFragment")
        }
    }
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }

    override fun onButtonClicked(text: String?) {
        textView12.text=text
    }


}
