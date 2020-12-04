package com.example.android_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Class DailyAdapter
 * Management of the display of Daily data.
 * @param   daily
 * @param   dailyClickListener
 */
class DailyAdapter(private val daily: ArrayList<Daily>,
                   private val dailyClickListener: DailyClickListener) : RecyclerView.Adapter<DailyViewHolder>() {
    /**
     * Creation of rows of daily
     * @param   parent      Parent activity
     * @param   viewType
     * @return  DailyViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_daily, parent, false)
        return DailyViewHolder(row)
    }

    /**
     * Display of daily while scrolling
     * @param   holder
     * @param   position    position of the daily in list
     */
    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        // Display the data in reverse direction
        val daily = this.daily[itemCount - 1 - position]

        holder.txvDate.text = convertDate(daily.date)
        holder.txvDayDeath.text = daily.day_death.toString()
        holder.txvDayPositive.text = daily.day_positive.toString()

        // Set the listener on the concerned daily
        holder.itemView.setOnClickListener { it ->
            dailyClickListener.onDailyClickListener(it, daily)
        }
    }

    /**
     * Get the size of the list daily
     * @return  int     Size of daily
     */
    override fun getItemCount(): Int {
        return this.daily.size
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

    /**
     * Update the list daily
     */
    fun updateItem(dailyToDisplay: List<Daily>) {
        daily.clear();
        daily.addAll(dailyToDisplay)
        notifyDataSetChanged();
    }
}