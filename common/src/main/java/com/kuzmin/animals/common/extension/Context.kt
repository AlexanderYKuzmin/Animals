package com.kuzmin.animals.common.extension

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import java.time.Duration

fun Context.showShortMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }.show()
}