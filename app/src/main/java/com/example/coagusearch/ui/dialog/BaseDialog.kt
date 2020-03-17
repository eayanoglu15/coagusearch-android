package com.example.coagusearch.ui.dialog

import android.app.Dialog
import android.view.View
import androidx.appcompat.app.AlertDialog

abstract class BaseDialog {

    abstract val dialogView: View
    abstract val builder: AlertDialog.Builder

    open var cancelable: Boolean = true

    open var dialog: Dialog? = null

    open fun onDialogCreated() = Unit

    open fun create(): Dialog {
        dialog = builder
                .setCancelable(cancelable)
                .create()

        onDialogCreated()
        return dialog!!
    }

    open fun onCancelListener(func: () -> Unit): AlertDialog.Builder? =
            builder.setOnCancelListener {
                func()
            }
}