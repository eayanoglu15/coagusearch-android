package com.example.coagusearch.ui.dialog


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.R

class InfoDialog(context: Context) : BaseDialog() {

    override val dialogView: View by lazy {
        //LayoutInflater.from(context).inflate(R.layout.info_dialog, null)
        LayoutInflater.from(context).inflate(R.layout.loading_lottie_dialog, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)
/*
    val titleText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialog_title_text_view)
    }

    val contentText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialog_content_text_view)
    }

    val closeButton: ImageButton by lazy {
        dialogView.findViewById<ImageButton>(R.id.dialog_close_button)
    }

    val actionButton: AppCompatButton by lazy {
        dialogView.findViewById<AppCompatButton>(R.id.dialog_action_button)
    }

    fun setMessage(message: String, isSelectable: Boolean = false) {
        contentText.text = message
        contentText.setTextIsSelectable(isSelectable)
    }

    fun showActionButton(buttonText: String){
        if (buttonText.isEmpty().not()) {
            actionButton.visibility = View.VISIBLE
            actionButton.text = buttonText
        } else actionButton.visibility = View.GONE
    }

    fun actionCloseButtonClickListener(func: (() -> Unit)? = null) =
            with(closeButton) {
                setClickListenerToDialog(func)
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