package com.example.android_project

import java.io.Serializable

class Dailyshelf : Serializable {
    private val storage = HashMap<Int, Daily>()

    fun addDaily(daily: Daily) {
        this.storage[daily.date] = daily
    }

    fun getDaily(date: Int): Daily? {
        return this.storage[date]
    }

    fun getAllDaily(): List<Daily> {
        return ArrayList(this.storage.values).sortedBy { daily -> daily.date }
    }

    fun getDailyBetweenDate(date1: Int, date2: Int): List<Daily> {
        val filteredStorage = this.storage.filter { it.value.date in date1..date2 }
        return ArrayList(filteredStorage.values).sortedBy { daily -> daily.date }
    }

    fun getTotalNumberOfDailyBooks(): Int {
        return this.storage.size
    }
}