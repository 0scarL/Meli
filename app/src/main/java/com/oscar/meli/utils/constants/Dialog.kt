package com.oscar.meli.utils.constants

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.oscar.meli.utils.constants.UiConstants.OPTION_OK

fun showMessage(context: Context, mensaje: String) {
    AlertDialog.Builder(context)
        .setMessage(mensaje)
        .setPositiveButton(OPTION_OK) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}