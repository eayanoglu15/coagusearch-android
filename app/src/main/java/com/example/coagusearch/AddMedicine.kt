package com.example.coagusearch

import android.app.Activity
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
    var freqPickerIsOn:Boolean=false
    var dosagePickerIsOn:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)
        dosagePicked.text = doslist[0]
        frequencyPicked.text=frelist[0]
        frequencyPicker.visibility = View.GONE
        dosagePicker.visibility = View.GONE
        editMedicineName.setOnClickListener{
            name_arrow.visibility=View.GONE
            multiAutoCompleteTextView.visibility=View.VISIBLE
            showSoftKeyboard(multiAutoCompleteTextView)
        }
        //AutoCompleteTextView
        multiAutoCompleteTextView.visibility=View.GONE
        val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,medList)
        multiAutoCompleteTextView.setAdapter(arrayAdapter)
        multiAutoCompleteTextView.visibility=View.GONE

        multiAutoCompleteTextView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            closeSoftKeyboard(multiAutoCompleteTextView)
            multiAutoCompleteTextView.visibility=View.GONE
            name_arrow.visibility=View.VISIBLE
            medName.text=multiAutoCompleteTextView.text

        }
        multiAutoCompleteTextView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //Perform Code
                closeSoftKeyboard(multiAutoCompleteTextView)
                multiAutoCompleteTextView.visibility=View.GONE
                name_arrow.visibility=View.VISIBLE
                medName.text=multiAutoCompleteTextView.text

                return@OnKeyListener true
            }
            false
        })
        //DosagePicker
        dosagePicker.maxValue=doslist.size-1
        dosagePicker.minValue=0
        dosagePicker.displayedValues=doslist
        dosagePicker.wrapSelectorWheel=false
        dosagePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        dosagePicker.setOnValueChangedListener { picker, oldVal, newVal ->
            dosagePicked.text = doslist[dosagePicker.value]
        }
        dosagePicker.setOnClickListener {
            if(dosagePickerIsOn) {
                dosagePicker.visibility = View.GONE
                dosagePickerIsOn=false
                ocDosagePicker.setImageResource(R.drawable.downarrow)
            }

        }
        ocDosagePicker.setOnClickListener{
            if(dosagePickerIsOn) {
                dosagePicker.visibility = View.GONE
                dosagePickerIsOn=false
                ocDosagePicker.setImageResource(R.drawable.downarrow)
            }
            else{
                dosagePicker.visibility = View.VISIBLE
                dosagePickerIsOn=true
                ocDosagePicker.setImageResource(R.drawable.uparrow)
            }
        }
        //FrequencyPicker
        frequencyPicker.maxValue=frelist.size-1
        frequencyPicker.minValue=0
        frequencyPicker.displayedValues=frelist
        frequencyPicker.wrapSelectorWheel=false
        frequencyPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        frequencyPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to text view
            frequencyPicked.text = frelist[frequencyPicker.value]
        }
        frequencyPicker.setOnClickListener {
            if(freqPickerIsOn) {
                frequencyPicker.visibility = View.GONE
                freqPickerIsOn=false
                ocFrequencyPicker.setImageResource(R.drawable.downarrow)
            }

        }
        ocFrequencyPicker.setOnClickListener{
            if(freqPickerIsOn) {
                frequencyPicker.visibility = View.GONE
                freqPickerIsOn=false
                ocFrequencyPicker.setImageResource(R.drawable.downarrow)
            }
            else{
                frequencyPicker.visibility = View.VISIBLE
                freqPickerIsOn=true
                ocFrequencyPicker.setImageResource(R.drawable.uparrow)
            }
        }
        MedicinePageAddButton.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
        }
        addMedicinePageGoBack.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
        }
    }
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
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

    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

}

