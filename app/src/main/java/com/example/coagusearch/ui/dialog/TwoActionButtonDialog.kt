package com.example.coagusearch.ui.dialog


import android.content.Context
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import com.example.coagusearch.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TwoActionButtonDialog(context: Context) : BaseDialog() {

    override val dialogView: View by lazy {
        //LayoutInflater.from(context).inflate(R.layout.two_action_button_dialog, null)
        LayoutInflater.from(context).inflate(R.layout.loading_lottie_dialog, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)
/*
    val titleText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialog_title_text_view)
    }

    val contentLayout: LinearLayout by lazy {
        dialogView.findViewById<LinearLayout>(R.id.dialog_content_layout)
    }

    val contactUsInputLayout: TextInputLayout by lazy {
        dialogView.findViewById<TextInputLayout>(R.id.contact_us_input_layout)
    }

    val contactUsInputEditText: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.contact_us_text_input_text)
    }

    val negativeButton: AppCompatButton by lazy {
        dialogView.findViewById<AppCompatButton>(R.id.dialog_negative_button)
    }

    val positiveButton: AppCompatButton by lazy {
        dialogView.findViewById<AppCompatButton>(R.id.dialog_positive_button)
    }

    fun setMessage(message: String) {
        val textView = TextView(builder.context)
        textView.textSize = 18F
        textView.text = message
        textView.typeface = ResourcesCompat.getFont(builder.context, R.font.open_sans_condensed_light)

        contentLayout.removeAllViews()
        contentLayout.addView(textView)
    }

    fun showContactUsLayout() {
        contactUsInputLayout.visibility = View.VISIBLE
        contactUsInputEditText.setSingleLine(false)
        contactUsInputEditText.maxHeight = 200F.toPx().toInt()
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(512)
        contactUsInputEditText.filters = filterArray
    }

    fun getMessage(): String {
        return contactUsInputEditText.text.toString()
    }

    fun negativeButtonClickListener(func: (() -> Unit)? = null) =
            with(negativeButton) {
                setClickListenerToDialog(func)
            }

    fun positiveButtonClickListener(func: (() -> Unit)? = null) =
            with(positiveButton) {
                setClickListenerToDialog(func)
            }

    private fun View.setClickListenerToDialog(func: (() -> Unit)?) =
            setSafeOnClickListener {
                func?.invoke()
                dialog?.dismiss()
            }

 */

}