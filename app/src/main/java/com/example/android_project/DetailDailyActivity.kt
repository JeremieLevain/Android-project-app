package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_daily.*
import java.text.DecimalFormat

class DetailDailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_daily)

        val bundle: Bundle? = this.intent.extras
        val daily: Daily
        if (bundle != null) {
            daily = bundle.getSerializable("daily") as Daily

            this.r_detail_daily_txv_date.text = convertDate(daily.date)
            this.r_detail_daily_txv_total_death.text = daily.total_death.toString()
            this.r_detail_daily_txv_total_positive.text = daily.total_positive.toString()
            this.r_detail_daily_txv_total_negative.text = daily.total_negative.toString()
            this.r_detail_daily_txv_day_ventilator.text = daily.onVentilatorCurrently.toString()
            this.r_detail_daily_txv_day_death.text = daily.day_death.toString()
            this.r_detail_daily_txv_day_positive.text = daily.day_positive.toString()
        }
    }

    fun exit(view: View) {
        finish()
    }

    fun convertDate(date: Int): String {
        val formater = DecimalFormat("00")

        val year = date/10000
        val month = (date-year*10000)/100
        val day = date-year*10000-month*100

        return formater.format(day)+'/'+formater.format(month)+'/'+year.toString()
    }
}