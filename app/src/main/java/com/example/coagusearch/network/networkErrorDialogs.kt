package com.example.coagusearch.network

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.R

fun onFailureDialog(context: Context,error:String){
    val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
    builder.setTitle("Error")
    builder.setMessage(error)
    builder.setPositiveButton("OK"){dialog, which ->
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}