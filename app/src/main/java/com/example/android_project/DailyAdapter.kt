package com.example.android_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DailyAdapter(private val daily: ArrayList<Daily>) : RecyclerView.Adapter<DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_daily, parent, false)
        return DailyViewHolder(row)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val (date, dayDeath, dayPositive) = this.daily[position]

        holder.txvDate.text = date.toString()
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
}