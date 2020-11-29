package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private val dailyshelf = Dailyshelf()
    private lateinit var dailyService: DailyService;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://DailyUScoronavirus-App-JLN-RFD.cleverapps.io")
            .build()

        dailyService = retrofit.create(dailyService::class.java)

        dailyService.getAllDaily().enqueue(object : Callback<ArrayList<Daily>> {
            override fun onResponse(
                call: Call<ArrayList<Daily>>,
                response: Response<ArrayList<Daily>>
            ) {
                val allBooks = response.body()
                allBooks?.forEach {
                    dailyshelf.addDaily(it)
                }
                displayList()
            }

            override fun onFailure(call: Call<ArrayList<Daily>>, t: Throwable) {
                displayErrorToast(t)
            }
        })

    }

    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Network error ${t.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun displayList() {
        val bookListFragment = DailyListFragment.newInstance(dailyshelf.getAllDaily())

        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_lyt_container, bookListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}