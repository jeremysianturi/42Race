package com.example.fortytworace.helper

import android.view.View

object Debounce {

    fun View.onThrottledClick(
        throttleDelay: Long = 500L,
        onClick: (View) -> Unit
    ) {
        setOnClickListener {
            onClick(this)
            isClickable = false
            postDelayed({ isClickable = true }, throttleDelay)
        }
    }
}