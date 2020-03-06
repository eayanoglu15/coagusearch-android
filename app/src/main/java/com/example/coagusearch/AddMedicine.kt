package com.example.coagusearch

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_medicine.*


class AddMedicine : AppCompatActivity() {
    val medList=arrayOf("Parol","Apranax","Etol Fort","Aferin","Buscopan","Arveles","Ahmet","ali","Mehmet")
    val doslist= arrayOf("1 dos","2 dos","3 dos","4 dos")
    val frelist= arrayOf("Once A Day","Twice A Day","After Breakfast","Before Breakfast","Before Lunch","After Lunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)
        multiAutoCompleteTextView.visibility=View.GONE
        val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,medList)
        multiAutoCompleteTextView.setAdapter(arrayAdapter)
        multiAutoCompleteTextView.visibility=View.GONE
        dospick.maxValue=3
        dospick.minValue=0
        dospick.displayedValues=doslist
        dospick.wrapSelectorWheel=false
        dospick.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        dospick.setOnValueChangedListener { picker, oldVal, newVal ->
           val ss=doslist[dospick.value]
            //Display the newly selected number to text view
            kkkkk.text = ss
        }
        dospick.setOnClickListener {
            dospick.visibility=View.GONE
        }
        freqpick.maxValue=frelist.size-1
        freqpick.minValue=0
        freqpick.displayedValues=frelist
        freqpick.wrapSelectorWheel=false
        freqpick.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        freqpick.setOnValueChangedListener { picker, oldVal, newVal ->
            val ss=frelist[freqpick.value]
            //Display the newly selected number to text view
            freqMedMed.text = ss
        }
        freqpick.setOnClickListener {
            freqpick.visibility=View.GONE
        }
        opendos.setOnClickListener{
            dospick.visibility=View.VISIBLE
        }
        editMedicineName.setOnClickListener{

            name_arrow.visibility=View.GONE
            multiAutoCompleteTextView.visibility=View.VISIBLE

            showSoftKeyboard(multiAutoCompleteTextView)
        }
        multiAutoCompleteTextView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            multiAutoCompleteTextView.visibility=View.GONE
            name_arrow.visibility=View.VISIBLE
            medName.text=multiAutoCompleteTextView.text

        }
        multiAutoCompleteTextView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //Perform Code
                multiAutoCompleteTextView.visibility=View.GONE
                name_arrow.visibility=View.VISIBLE
                medName.text=multiAutoCompleteTextView.text
                return@OnKeyListener true
            }
            false
        })
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

            // here is one more tricky issue
            // imm.showSoftInputMethod doesn't work well
            // and imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0) doesn't work well for all cases too
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}

