package com.example.coagusearch.patient

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.RegularMedication.model.RegularMedicationRepository
import com.example.coagusearch.network.RegularMedication.request.DeleteMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.request.KeyType
import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import kotlinx.android.synthetic.main.activity_add_medicine.*
import org.koin.android.ext.android.get


class AddMedicine : AppCompatActivity() {
    var medList = arrayOf("", "")
    var doslist = arrayOf("0.5", "1.0", "1.5", "2.0", "2.5", "3.0")
    var frelist = arrayOf("", "s")
    var freqPickerIsOn: Boolean = false
    var dosagePickerIsOn: Boolean = false
    var medMap: HashMap<String, String>? = null
    var freqMap: HashMap<String, String>? = null
    var mode: MedicineInfoType = MedicineInfoType.CUSTOM
    var key: String? = null
    var uuid: String? = null
    var customText: String? = null
    var frequency: String? = null
    var dosage: Double? = null
    var id: KeyType? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)
        medMap = UserInfoSingleton.instance.getMedineHashMap()
        freqMap = UserInfoSingleton.instance.getFrequencyHashMap()
        frelist = UserInfoSingleton.instance.getFrequencyNames() as Array<String>
        medList = UserInfoSingleton.instance.getMedicineNames() as Array<String>
        dosage = doslist[0].toDouble()
        frequency = freqMap!!.get(frelist[0])
        dosagePicked.text = doslist[0]
        frequencyPicked.text = frelist[0]
        frequencyPicker.visibility = View.GONE
        dosagePicker.visibility = View.GONE
        editMedicineName.setOnClickListener {
            name_arrow.visibility = View.GONE
            multiAutoCompleteTextView.visibility = View.VISIBLE
            showSoftKeyboard(multiAutoCompleteTextView)
        }

        //AutoCompleteTextView
        multiAutoCompleteTextView.visibility = View.GONE
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medList)
        multiAutoCompleteTextView.setAdapter(arrayAdapter)
        multiAutoCompleteTextView.visibility = View.GONE

        multiAutoCompleteTextView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            closeSoftKeyboard(multiAutoCompleteTextView)
            multiAutoCompleteTextView.visibility = View.GONE
            name_arrow.visibility = View.VISIBLE
            medName.text = multiAutoCompleteTextView.text
            key = medMap!!.get(multiAutoCompleteTextView.text.toString())
            mode = MedicineInfoType.KEY
            println(key)
        }

        multiAutoCompleteTextView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //Perform Code
                closeSoftKeyboard(multiAutoCompleteTextView)
                multiAutoCompleteTextView.visibility = View.GONE
                name_arrow.visibility = View.VISIBLE
                medName.text = multiAutoCompleteTextView.text
                mode = MedicineInfoType.CUSTOM
                customText = multiAutoCompleteTextView.text.toString()
                println(customText)
                return@OnKeyListener true
            }
            false
        })

        //DosagePicker
        dosagePicker.maxValue = doslist.size - 1
        dosagePicker.minValue = 0
        dosagePicker.displayedValues = doslist
        dosagePicker.wrapSelectorWheel = false
        dosagePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        dosagePicker.setOnValueChangedListener { picker, oldVal, newVal ->
            dosagePicked.text = doslist[dosagePicker.value]
            dosage = (dosagePicked.text as String).toDouble()
        }
        dosagePicker.setOnClickListener {
            if (dosagePickerIsOn) {
                dosagePicker.visibility = View.GONE
                dosagePickerIsOn = false
                ocDosagePicker.setImageResource(R.drawable.downarrow)
            }
        }
        ocDosagePicker.setOnClickListener {
            if (dosagePickerIsOn) {
                dosagePicker.visibility = View.GONE
                dosagePickerIsOn = false
                ocDosagePicker.setImageResource(R.drawable.downarrow)
            } else {
                dosagePicker.visibility = View.VISIBLE
                dosagePickerIsOn = true
                ocDosagePicker.setImageResource(R.drawable.uparrow)
            }
        }
        //FrequencyPicker
        frequencyPicker.maxValue = frelist.size - 1
        frequencyPicker.minValue = 0
        frequencyPicker.displayedValues = frelist
        frequencyPicker.wrapSelectorWheel = false
        frequencyPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
        frequencyPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to text view
            frequencyPicked.text = frelist[frequencyPicker.value]
            frequency = freqMap!!.get(frelist[frequencyPicker.value])
        }
        frequencyPicker.setOnClickListener {
            if (freqPickerIsOn) {
                frequencyPicker.visibility = View.GONE
                freqPickerIsOn = false
                ocFrequencyPicker.setImageResource(R.drawable.downarrow)
            }

        }
        ocFrequencyPicker.setOnClickListener {
            if (freqPickerIsOn) {
                frequencyPicker.visibility = View.GONE
                freqPickerIsOn = false
                ocFrequencyPicker.setImageResource(R.drawable.downarrow)
            } else {
                frequencyPicker.visibility = View.VISIBLE
                freqPickerIsOn = true
                ocFrequencyPicker.setImageResource(R.drawable.uparrow)
            }
        }
        MedicinePageAddButton.setOnClickListener {
            if (mode == MedicineInfoType.CUSTOM && (customText.equals("") || customText == null)) {
                Toast.makeText(this, "Please select a medicine name", Toast.LENGTH_SHORT).show()

            } else if (mode == MedicineInfoType.KEY && (key.equals("") || key == null)) {
                Toast.makeText(this, "Please select a medicine name", Toast.LENGTH_SHORT).show()
            } else {
                println("name is " + key + "  " + customText)
                saveMedicine()
                finish()
                overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
            }
        }
        addMedicinePageGoBack.setOnClickListener {
            finish()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }
        val bundle: Bundle? = intent.extras
        var editOrNew = bundle!!.getString("type")
        if (editOrNew.equals("edit")) {
            id = bundle.get("id") as KeyType
            frequencyPicked.text = bundle.getString("freq")
            dosagePicked.text = bundle.getString("dos")
            medName.text = bundle.getString("Name")
            MedicinePageRemoveButton.setOnClickListener {
                val medRepository: RegularMedicationRepository = get()
                medRepository.deleteMedicine(DeleteMedicineInfoRequest(id!!), this)
            }
        } else {
            MedicinePageRemoveButton.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    fun saveMedicine() {
        val medRepository: RegularMedicationRepository = get()
        medRepository.saveMedicine(
            SaveMedicineInfoRequest(
                id,
                mode,
                key,
                customText,
                frequency,
                dosage
            ), this
        )

    }


}

