package com.example.android_project

import java.io.Serializable

/**
 * Class DailyShelf
 * List of daily data recovered with the API on Clever Cloud to use in the app
 * @property    storage     List of Daily data
 */
class DailyShelf : Serializable {
    private val storage = HashMap<Int, Daily>()

    /**
     * Create a daily in storage
     * @param   daily
     */
    fun addDaily(daily: Daily) {
        this.storage[daily.date] = daily
    }

    /**
     * Get a daily by date
     * @param   date
     * @return  Daily | null
     */
    fun getDaily(date: Int): Daily? {
        return this.storage[date]
    }

    /**
     * Get all daily in the list
     * @return  List
     */
    fun getAllDaily(): List<Daily> {
        return ArrayList(this.storage.values).sortedBy { daily -> daily.date }
    }

    /**
     * Get daily in a range of date
     * @param   date1
     * @param   date2
     * @return  List
     */
    fun getDailyBetweenDate(date1: Int, date2: Int): List<Daily> {
        val filteredStorage = this.storage.filter { it.value.date in date1..date2 }
        return ArrayList(filteredStorage.values).sortedBy { daily -> daily.date }
    }

    /**
     * Get the number of element in storage
     * @return  Int
     */
    fun getTotalNumberOfDailyBooks(): Int {
        return this.storage.size
    }
}