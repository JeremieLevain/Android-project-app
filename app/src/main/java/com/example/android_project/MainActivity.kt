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

/**
 * Class MainActivity
 * Main window of the application
 * @property    TAG
 * @property    dailyShelf
 * @property    dailyService
 * @property    buttonMa3D
 * @property    buttonW
 * @property    buttonM
 * @property    buttonY
 * @property    buttonDEATH
 * @property    buttonPOSITIVE
 */
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName

    // HTTP request
    private val dailyShelf = DailyShelf()
    private lateinit var dailyService: DailyService

    // Variables to display graphs
    private var buttonMa3D = false
    private var buttonW = true
    private var buttonM = false
    private var buttonY = false
    private var buttonDEATH = true
    private var buttonPOSITIVE = false

    /**
     * Creation of the MainActivity
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect retrofit to the API
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://DailyUScoronavirus-App-JLN-RFD.cleverapps.io")
            .build()

        // Create all HTTP request
        dailyService = retrofit.create(DailyService::class.java)

        // Recover data and save them in dailyShelf
        dailyService.getAllDaily().enqueue(object : Callback<ArrayList<Daily>> {
            override fun onResponse(call: Call<ArrayList<Daily>>, response: Response<ArrayList<Daily>>) {
                val allDaily = response.body()
                allDaily?.forEach {
                    dailyShelf.addDaily(it)
                }
                displayList()
            }

            override fun onFailure(call: Call<ArrayList<Daily>>, t: Throwable) {
                displayErrorToast(t)
            }
        })
    }

    /**
     * Display of an error message for the getAllDaily request
     */
    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Network error ${t.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Display the list fragment of daily
     */
    private fun displayList() {
        val dailyListFragment = DailyListFragment.newInstance(dailyShelf.getAllDaily())

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, dailyListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    /**
     * Method to go to documentation of the app
     * @param   view
     */
    fun goToDocumentation(view: View) {
        val infoFragment = InfoFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, infoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    /**
     * Method to switch to graph window
     * @param   view
     */
    fun goToGraph(view: View) {
        val graphFragment = GraphFragment.newInstance(
                dailyShelf.getAllDaily(),
                buttonMa3D,
                buttonW,
                buttonM,
                buttonY,
                buttonDEATH,
                buttonPOSITIVE
        )

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, graphFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    /**
     * Method to return to list fragment
     * @param   view
     */
    fun goToDailyList(view: View) {
        displayList()
    }

    /**
     * Method to see graph in 3D
     * @param   view
     */
    fun goToMa3D(view: View) {
        buttonMa3D = !buttonMa3D
        goToGraph(view)
    }

    /**
     * Method to see data of the current week
     * @param   view
     */
    fun goToW(view: View) {
        if (!buttonW) {
            buttonW = true
            buttonM = false
            buttonY = false
        }
        goToGraph(view)
    }

    /**
     * Method to see data of the current month
     * @param   view
     */
    fun goToM(view: View) {
        if (!buttonM) {
            buttonM = true
            buttonW = false
            buttonY = false
        }
        goToGraph(view)
    }

    /**
     * Method to see data of the current year
     * @param   view
     */
    fun goToY(view: View) {
        if (!buttonY) {
            buttonY = true
            buttonW = false
            buttonM = false
        }
        goToGraph(view)
    }

    /**
     * Method to see a graph with the number of death
     * @param   view
     */
    fun goToDEATH(view: View) {
        buttonDEATH = !buttonDEATH
        buttonPOSITIVE = !buttonPOSITIVE
        goToGraph(view)
    }

    /**
     * Method to see a graph with the number of positive cases
     * @param   view
     */
    fun goToPOSITIVE(view: View) {
        buttonPOSITIVE = !buttonPOSITIVE
        buttonDEATH = !buttonDEATH
        goToGraph(view)
    }
}