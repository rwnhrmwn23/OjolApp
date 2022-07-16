package com.onedev.ojolapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation

private lateinit var preferenceManager: PreferenceManager

@Synchronized
fun getInstance(context: Context): PreferenceManager {
    preferenceManager = PreferenceManager(context)
    return preferenceManager
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.navigateUp() {
    val controller = this.let { Navigation.findNavController(this) }
    controller.navigateUp()
}