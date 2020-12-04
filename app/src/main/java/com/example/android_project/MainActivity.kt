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

    // HTTP request
    private val dailyshelf = Dailyshelf()
    private lateinit var dailyService: DailyService

    // Fragment displaying graphs
    private var button_MA_3D = false
    private var button_W = false
    private var button_M = false
    private var button_Y = false
    private var button_DEATH = true
    private var button_POSITIVE = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://DailyUScoronavirus-App-JLN-RFD.cleverapps.io")
            .build()

        dailyService = retrofit.create(DailyService::class.java)

        dailyService.getAllDaily().enqueue(object : Callback<ArrayList<Daily>> {
            override fun onResponse(call: Call<ArrayList<Daily>>, response: Response<ArrayList<Daily>>) {
                val allDaily = response.body()
                allDaily?.forEach {
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
        val dailyListFragment = DailyListFragment.newInstance(dailyshelf.getAllDaily())

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, dailyListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    fun goToDocumentation(view: View) {
        val infoFragment = InfoFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, infoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    fun goToGraph(view: View) {
        val graphFragment = GraphFragment.newInstance(
                dailyshelf.getAllDaily(),
                button_MA_3D,
                button_W,
                button_M,
                button_Y,
                button_DEATH,
                button_POSITIVE
        )

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, graphFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    fun goToDailyList(view: View) {
        displayList()
    }

    fun goToMA_3D(view: View) {
        button_MA_3D = !button_MA_3D
        goToGraph(view)
    }

    fun goToW(view: View) {
        button_W = !button_W
        button_M = false
        button_Y = false
        goToGraph(view)
    }

    fun goToM(view: View) {
        button_M = !button_M
        button_W = false
        button_Y = false
        goToGraph(view)
    }

    fun goToY(view: View) {
        button_Y = !button_Y
        button_W = false
        button_M = false
        goToGraph(view)
    }

    fun goToDEATH(view: View) {
        button_DEATH = !button_DEATH
        button_POSITIVE = !button_POSITIVE
        goToGraph(view)
    }

    fun goToPOSITIVE(view: View) {
        button_POSITIVE = !button_POSITIVE
        button_DEATH = !button_DEATH
        goToGraph(view)
    }
}