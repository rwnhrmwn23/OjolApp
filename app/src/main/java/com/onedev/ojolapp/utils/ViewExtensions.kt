package com.onedev.ojolapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation

private lateinit var preferenceManager: PreferenceManager

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.putStringPreference(key: String, value: String) {
    preferenceManager = PreferenceManager(this)
    preferenceManager.putString(key, value)
}

fun Context.getStringPreference(key: String): String {
    preferenceManager = PreferenceManager(this)
    return preferenceManager.getString(key)
}

fun Context.putBooleanPreference(key: String, value: Boolean) {
    preferenceManager = PreferenceManager(this)
    preferenceManager.putBoolean(key, value)
}

fun Context.getBooleanPreference(key: String): Boolean {
    preferenceManager = PreferenceManager(this)
    return preferenceManager.getBoolean(key)
}

fun Context.clearPreference() {
    preferenceManager = PreferenceManager(this)
    preferenceManager.clearPreference()
}

fun View.navigateUp() {
    val controller = this.let { Navigation.findNavController(this) }
    controller.navigateUp()
}