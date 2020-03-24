package com.example.coagusearch.ui.dialog

import android.app.Dialog
import android.content.Context

fun showProgressLoading(loading: Boolean,context: Context) {
    if (loading) {
        if (LoadingProgressSingleton.dialog == null) {
            createLoadingDialog(true,context)
        }
        LoadingProgressSingleton.dialog?.show()
    } else {
        LoadingProgressSingleton.dialog?.dismiss()
        LoadingProgressSingleton.dialog = null
    }
}
private fun createLoadingDialog(isProgressDialog: Boolean = false,context: Context) {
    fun getDialog(): Dialog {
        return if (isProgressDialog) {
            LoadingProgressDialog(context).create()
        } else {
            LoadingDialog(context).create()
        }
    }
    LoadingProgressSingleton.dialog = getDialog()
}