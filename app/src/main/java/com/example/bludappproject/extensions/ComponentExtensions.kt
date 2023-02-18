package com.example.bludappproject.extensions

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import com.example.bludappproject.R
import com.google.android.material.snackbar.Snackbar

/**
 * Animate this view with a Fade in
 */
fun View.animateViewWithFade(context: Context) {
    val fade = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
    startAnimation(fade)
}

fun EditText.checkIfEditIsEmpty() = !(TextUtils.isEmpty(this.text.toString()))

fun View.snack(str: String) = Snackbar.make(this, str, Snackbar.LENGTH_SHORT).show()