    package com.example.coagusearch

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.NumberPicker
    import android.widget.Toast
    import com.example.coagusearch.network.Appointment.model.AppointmentRepository
    import com.example.coagusearch.network.Appointment.request.SaveAppointmentRequest
    import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
    import com.example.coagusearch.ui.dialog.showProgressLoading
    import kotlinx.android.synthetic.main.activity_new_appointment.*
    import org.koin.android.ext.android.get

    class NewAppointment : AppCompatActivity() {
        private var doctorPickerIsON:Boolean=false
        private var datePickerIsON:Boolean=false
        private var slotPickerIsON:Boolean=false
        var appointmentResponse: WeeklyAvailabilityResponse? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_new_appointment)
            timeSlotHour.visibility=View.GONE
            appointmentDatePicker.visibility=View.GONE
            doctorPicker.visibility=View.GONE
            setListenersNewAppointment()
            showProgressLoading(true,this)
            val appointmentRepository: AppointmentRepository = get()
            appointmentResponse=appointmentRepository.availableTimeSlotsForAppointment(this)
        }

        override fun onBackPressed() {
            finish()
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
        }

        fun gotTheDate(appointmentResponse: WeeklyAvailabilityResponse){
            this.appointmentResponse=appointmentResponse
            doctorCardYourDoctor.text=appointmentResponse.doctorName+" "+appointmentResponse.doctorSurname
                var dayList=ArrayList<String>()
                val iterator=appointmentResponse.week.listIterator()
                while (iterator.hasNext()) {
                    dayList.add(iterator.next().toString())
                }
            val array = arrayOfNulls<String>(dayList.size)
            dayList.toArray(array)
            appointmentDatePicker.maxValue=dayList.size-1
            appointmentDatePicker.minValue=0
            appointmentDatePicker.displayedValues= array
            appointmentDatePicker.wrapSelectorWheel=false
            timeSlotHour.wrapSelectorWheel=false
            timeSlotHour.descendantFocusability=NumberPicker.FOCUS_BLOCK_DESCENDANTS
            appointmentDatePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS;
            dateCardYourDate.text=array[0]
            val firstSlot=appointmentResponse.week[0].getAvailableHours()
            timeSlotHour.maxValue=firstSlot.size-1
            timeSlotHour.minValue=0
            timeSlotHour.displayedValues=firstSlot
            appointmentDatePicker.setOnValueChangedListener { numberPicker, oldVal, newVal ->
                dateCardYourDate.text=array[newVal]
                val availableSlot=appointmentResponse.week[newVal].getAvailableHours()
                timeSlotHour.maxValue=availableSlot.size-1
                timeSlotHour.minValue=0
                timeSlotHour.displayedValues=availableSlot
                timeSlotHour.setOnValueChangedListener{numberPicker, i, i2 ->
                    timeSlotCardYourSlot.text=availableSlot[i2]
                }
            }
        }

        private fun setListenersNewAppointment(){
            /*
            doctorPicker.setOnClickListener {
                if(doctorPickerIsON) {
                    doctorPicker.visibility = View.GONE
                    doctorPickerIsON=false
                    ocYourDoctor.setImageResource(R.drawable.downarrow)
                }
            }

            ocYourDoctor.setOnClickListener{
                if(doctorPickerIsON) {
                    doctorPicker.visibility = View.GONE
                    doctorPickerIsON=false
                    ocYourDoctor.setImageResource(R.drawable.downarrow)
                }
                else{
                    doctorPicker.visibility = View.VISIBLE
                    doctorPickerIsON=true
                    ocYourDoctor.setImageResource(R.drawable.uparrow)
                }
            }
            */
            appointmentDatePicker.setOnClickListener {
                if(datePickerIsON) {
                    appointmentDatePicker.visibility = View.GONE
                    datePickerIsON=false
                    ocAppointmentDate.setImageResource(R.drawable.downarrow)
                }
            }

            ocAppointmentDate.setOnClickListener{
                if(datePickerIsON) {
                    appointmentDatePicker.visibility = View.GONE
                    datePickerIsON=false
                    ocAppointmentDate.setImageResource(R.drawable.downarrow)
                }
                else{
                    appointmentDatePicker.visibility = View.VISIBLE
                    datePickerIsON=true
                    ocAppointmentDate.setImageResource(R.drawable.uparrow)
                }
            }
            timeSlotHour.setOnClickListener {
                if(slotPickerIsON) {
                    timeSlotHour.visibility = View.GONE
                    slotPickerIsON=false
                    ocTimeSlot.setImageResource(R.drawable.downarrow)
                }
            }
            ocTimeSlot.setOnClickListener{
                if(slotPickerIsON) {
                    timeSlotHour.visibility = View.GONE
                    slotPickerIsON=false
                    ocTimeSlot.setImageResource(R.drawable.downarrow)
                }
                else{
                    timeSlotHour.visibility = View.VISIBLE
                    slotPickerIsON=true
                    ocTimeSlot.setImageResource(R.drawable.uparrow)
                }
            }
            newAppointmentGoBack.setOnClickListener{
                finish()
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
            }
            request_Appointment.setOnClickListener{
                if(dateCardYourDate.text==""){
                    Toast.makeText(this,"Please select a date", Toast.LENGTH_SHORT).show()
                }
                else if (timeSlotCardYourSlot.text==""){
                    Toast.makeText(this,"Please select a time slot", Toast.LENGTH_SHORT).show()
                }
                else{
                        val date=dateCardYourDate.text.split("/")
                        val time=timeSlotCardYourSlot.text.split(":")
                        println(date.toString())
                        println(time.toString())
                        val appointmentRepository: AppointmentRepository = get()
                        appointmentRepository.saveAppointmentForUser(SaveAppointmentRequest(date[0].toInt(),date[1].toInt(),
                            date[2].toInt(),time[0].toInt(),time[1].toInt()),this)
                        showProgressLoading(true,this)
                }


            }

        }

    }
