package com.example.android_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class DailyAdapter(private val daily: ArrayList<Daily>) : RecyclerView.Adapter<DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_daily, parent, false)
        return DailyViewHolder(row)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        // Display the data in reverse direction
        val (   date,
                total_death,
                total_positive,
                total_negativet,
                dayDeath,
                dayPositive
        ) = this.daily[itemCount - 1 - position]

        holder.txvDate.text = convertDate(date)
        holder.txvDayDeath.text = dayDeath.toString()
        holder.txvDayPositive.text = dayPositive.toString()
    }

    override fun getItemCount(): Int {
        return this.daily.size
    }

    fun updateItem(dailyToDisplay: List<Daily>) {
        daily.clear();
        daily.addAll(dailyToDisplay)
        notifyDataSetChanged();
    }

    fun convertDate(date: Int): String {
        val formater = DecimalFormat("00")

        val year = date/10000
        val month = (date-year*10000)/100
        val day = date-year*10000-month*100

        return formater.format(day)+'/'+formater.format(month)+'/'+year.toString()
    }
}