package com.example.android_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

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