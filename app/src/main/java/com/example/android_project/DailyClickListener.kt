package com.example.android_project
import android.view.View

/**
 * Interface DailyClickListener
 * Listen click events on an element of the list of daily
 */
interface DailyClickListener {
    /**
     * Exception method for click events
     * @param   view    Parent activity
     * @param   daily   Daily with detail to display
     */
    fun onDailyClickListener(view: View, daily: Daily)
}