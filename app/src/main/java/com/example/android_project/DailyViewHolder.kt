package com.example.android_project

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvDate: TextView = itemView.findViewById(R.id.r_daily_txv_date)
    var txvDayDeath: TextView = itemView.findViewById(R.id.r_daily_txv_day_death)
    var txvDayPositive: TextView = itemView.findViewById(R.id.r_daily_txv_day_positive)
}