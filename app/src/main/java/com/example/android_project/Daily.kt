package com.example.android_project

import java.io.Serializable
import java.text.DecimalFormat

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