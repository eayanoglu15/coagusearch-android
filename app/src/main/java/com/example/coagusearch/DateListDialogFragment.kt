package com.example.coagusearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import kotlinx.android.synthetic.main.bottomsheet.*
import kotlinx.android.synthetic.main.bottomsheet.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


// TODO: Customize parameter argument names

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    DateListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class DateListDialogFragment : BottomSheetDialogFragment() {
    private var mListener: BottomSheetListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.bottomsheet, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doneButton.setOnClickListener {
            mListener!!.onButtonClicked(birthday.dayOfMonth.toString()+"/"+birthday.month.toString()+"/"+birthday.year.toString())
            dismiss()
        }
    }


    interface BottomSheetListener {
        fun onButtonClicked(text: String?)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as BottomSheetListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
            )
        }
    }



}
