package com.example.coagusearch.ui.dialog


import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class ActionButtonDialog(
    context: Context, override val dialogView: View,
    override val builder: AlertDialog.Builder
) : BaseDialog() {
/*
    override val dialogView: View by lazy {
        //LayoutInflater.from(context).inflate(R.layout.action_button_dialog, null)
        LayoutInflater.from(context).inflate(R.layout.action_button_dialog, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    val titleText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialog_title_text_view)
    }

    val contentLayout: LinearLayout by lazy {
        dialogView.findViewById<LinearLayout>(R.id.dialog_content_layout)
    }

    val actionButton: AppCompatButton by lazy {
        dialogView.findViewById<AppCompatButton>(R.id.dialog_action_button)
    }

    fun setMessage(message: String, isSelectable: Boolean = false) {
        val textView = TextView(builder.context)
        textView.textSize = 18F
        textView.text = message
        textView.typeface = ResourcesCompat.getFont(builder.context, R.font.open_sans_condensed_light)
        textView.setTextIsSelectable(isSelectable)

        contentLayout.removeAllViews()
        contentLayout.addView(textView)
    }

    fun setView(view: View) {
        contentLayout.removeAllViews()
        contentLayout.addView(view)
    }

    fun actionButtonClickListener(func: (() -> Unit)? = null) =
            with(actionButton) {
                setClickListenerToDialog(func)
            }

    private fun View.setClickListenerToDialog(func: (() -> Unit)?) =
            setSafeOnClickListener {
                func?.invoke()
                dialog?.dismiss()
            }

 */
}