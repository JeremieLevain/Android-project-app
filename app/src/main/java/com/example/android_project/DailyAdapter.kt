package com.example.android_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DailyAdapter(private val daily: ArrayList<Daily>,
                   private val cellClickListener: CellClickListener) : RecyclerView.Adapter<DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_daily, parent, false)
        return DailyViewHolder(row)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        // Display the data in reverse direction
        val daily = this.daily[itemCount - 1 - position]

        holder.txvDate.text = convertDate(daily.date)
        holder.txvDayDeath.text = daily.day_death.toString()
        holder.txvDayPositive.text = daily.day_positive.toString()

        holder.itemView.setOnClickListener { it ->
            cellClickListener.onCellClickListener(it, daily)
        }
    }

    override fun getItemCount(): Int {
        return this.daily.size
    }

    private fun convertDate(date: Int): String {
        val year = date.toString().substring(0,4)
        val month = date.toString().substring(4,6)
        val day = date.toString().substring(6,8)

        return "$day/$month/$year"
    }

    fun updateItem(dailyToDisplay: List<Daily>) {
        daily.clear();
        daily.addAll(dailyToDisplay)
        notifyDataSetChanged();
    }
}