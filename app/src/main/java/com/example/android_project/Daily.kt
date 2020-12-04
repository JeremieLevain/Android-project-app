package com.example.android_project

import java.io.Serializable

/**
 * Class Daily
 * Group of daily data about Covid-19 in USA.
 * @property    date
 * @property    total_death
 * @property    total_positive
 * @property    total_negative
 * @property    day_death
 * @property    day_positive
 * @property    onVentilatorCurrently
 * @property    favorite
 */
data class Daily(
    val date: Int,
    val total_death: Int,
    val total_positive: Int,
    val total_negative: Int,
    val day_death: Int,
    val day_positive: Int,
    val onVentilatorCurrently: Int,
    val favorite: Boolean
    ) : Serializable