package com.example.coagusearch.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.R


class LoadingDialog(val context: Context) : BaseDialog() {
    override val dialogView: View by lazy {
        //LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        LayoutInflater.from(context).inflate(R.layout.loading_lottie_dialog, null)
    }

    override val builder: AlertDialog.Builder =
        AlertDialog.Builder(context, R.style.DialogStyle).setView(dialogView)
/*
    val loadingIcon: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.dialog_loadingIcon)
    }

    override fun onDialogCreated() {
        super.onDialogCreated()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val spinningAnim = AnimationUtils.loadAnimation(context, R.anim.spinning)
        loadingIcon.startAnimation(spinningAnim)
    }

 */
}