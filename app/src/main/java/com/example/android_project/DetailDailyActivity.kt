package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DetailDailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_daily)
    }

    fun exit(view: View) {
        finish()
    }
}