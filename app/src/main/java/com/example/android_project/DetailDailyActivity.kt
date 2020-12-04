package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_daily.*

/**
 * Class DetailDailyActivity
 * Window of a daily's detail
 */
class DetailDailyActivity : AppCompatActivity() {
    /**
     * Creation of the window
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_daily)

        // Reception of the Daily object sent by MainActivity
        val bundle: Bundle? = this.intent.extras
        val daily: Daily
        if (bundle != null) {
            daily = bundle.getSerializable("daily") as Daily

            // Set the different views of activity_detail_daily.xml resource
            this.r_detail_daily_txv_date.text = convertDate(daily.date)
            this.r_detail_daily_txv_total_death.text = daily.total_death.toString()
            this.r_detail_daily_txv_total_positive.text = daily.total_positive.toString()
            this.r_detail_daily_txv_total_negative.text = daily.total_negative.toString()
            this.r_detail_daily_txv_day_ventilator.text = daily.onVentilatorCurrently.toString()
            this.r_detail_daily_txv_day_death.text = daily.day_death.toString()
            this.r_detail_daily_txv_day_positive.text = daily.day_positive.toString()
        }
    }

    /**
     * Return to the main window
     * @param   view
     */
    fun exit(view: View) {
        finish()
    }

    /**
     * Convert a date int in a string one
     * @param   date
     * @return  string  date in format "dd/mm/yyyy"
     */
    private fun convertDate(date: Int): String {
        val year = date.toString().substring(0,4)
        val month = date.toString().substring(4,6)
        val day = date.toString().substring(6,8)

        return "$day/$month/$year"
    }
}