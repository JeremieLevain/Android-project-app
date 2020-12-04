package com.example.android_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

private const val ARG_DATA = "ARG_DATA"
private const val ARG_BUTTON_MA_3D = "ARG_BUTTON_MA_3D"
private const val ARG_BUTTON_W = "ARG_BUTTON_W"
private const val ARG_BUTTON_M = "ARG_BUTTON_M"
private const val ARG_BUTTON_Y = "ARG_BUTTON_Y"
private const val ARG_BUTTON_DEATH = "ARG_BUTTON_DEATH"
private const val ARG_BUTTON_POSITIVE = "ARG_BUTTON_POSITIVE"


class GraphFragment : Fragment() {

    private lateinit var data: ArrayList<Daily>
    private var button_MA_3D = false
    private var button_W = false
    private var button_M = false
    private var button_Y = false
    private var button_DEATH = false
    private var button_POSITIVE = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable(ARG_DATA) as ArrayList<Daily>
            button_MA_3D = it.getSerializable(ARG_BUTTON_MA_3D) as Boolean
            button_W = it.getSerializable(ARG_BUTTON_W) as Boolean
            button_M = it.getSerializable(ARG_BUTTON_M) as Boolean
            button_Y = it.getSerializable(ARG_BUTTON_Y) as Boolean
            button_DEATH = it.getSerializable(ARG_BUTTON_DEATH) as Boolean
            button_POSITIVE = it.getSerializable(ARG_BUTTON_POSITIVE) as Boolean
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_graph, container, false)
        val graph = rootView.findViewById(R.id.graph) as GraphView
        val switch = rootView.findViewById(R.id.f_graph_switch_MA) as Switch

        if (button_MA_3D)
            switch.setChecked(true)
        else
            switch.setChecked(false)

        val currentDate = getCurrentDate()
        val previousWeekDate = getPreviousWeek()
        val previousMonthDate = getPreviousMonth()

        if(button_W) {
            val tab1 = getDailyBetweenDate(previousWeekDate,currentDate)
            val tab2 = Array(tab1.size) { i: Int ->
                DataPoint(i.toDouble(), tab1[i].day_death.toDouble())
            }
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(tab2)
            graph.addSeries(series)
        }

        if(button_M) {
            val tab1 = getDailyBetweenDate(previousMonthDate,currentDate)
            val tab2 = Array(tab1.size) { i: Int ->
                DataPoint(
                    i.toDouble(),
                    tab1[i].day_death.toDouble()
                )
            }
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(tab2)
            graph.addSeries(series)
        }

        if(button_Y) {
            val tab1=getDailyBetweenDate(20200101,currentDate)
            val tab2=Array(tab1.size) { i: Int ->
                DataPoint(
                    i.toDouble(),
                    tab1[i].day_death.toDouble()
                )
            }
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(tab2)
            graph.addSeries(series)
        }

        return rootView
    }

    private fun getDailyBetweenDate(date1: Int, date2: Int): List<Daily> {
        return ArrayList(this.data).filter { daily: Daily -> isDailyBetweenDate(date1, date2, daily)
        }.sortedByDescending { daily : Daily -> daily.date  }
    }

    private fun isDailyBetweenDate(date1: Int, date2 : Int, daily : Daily) : Boolean {
        var bool = false
        val year = daily.date.toString().substring(0, 4)
        val month = daily.date.toString().substring(4, 6)
        val day = daily.date.toString().substring(6, 8)

        val year1 = date1.toString().substring(0, 4)
        val month1 = date1.toString().substring(4, 6)
        val day1 = date1.toString().substring(6, 8)

        val year2 = date2.toString().substring(0, 4)
        val month2 = date2.toString().substring(4, 6)
        val day2 = date2.toString().substring(6, 8)

        if (year in year1..year2) {
            if (month == month1 && month == month2) {
                if (day in day1..day2) {
                    bool = true
                }
            }
            if (month == month1 && month < month2) {
                if (day >= day1)
                    bool = true
            }
            if (month > month1 && month == month2) {
                if(day <= day2)
                    bool = true
            }
            if (month > month1 && month < month2) {
                bool = true
            }
        }
        else
            bool = false
        return bool
    }

    private fun getCurrentDate(): Int {
        return ArrayList(data.sortedByDescending { daily : Daily -> daily.date })[0].date
    }

    private fun getPreviousWeek() : Int {
        return ArrayList(data.sortedByDescending { daily : Daily -> daily.date })[6].date
    }

    private fun getPreviousMonth() : Int {
        return ArrayList(data.sortedByDescending { daily : Daily -> daily.date })[31].date
    }

    companion object {
        @JvmStatic
        fun newInstance(data: List<Daily>, button_MA_3D : Boolean, button_W : Boolean, button_M : Boolean, button_Y : Boolean, button_DEATH : Boolean, button_POSITIVE : Boolean) =
            GraphFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_DATA, ArrayList(data))
                    putSerializable(ARG_BUTTON_MA_3D, button_MA_3D)
                    putSerializable(ARG_BUTTON_W, button_W)
                    putSerializable(ARG_BUTTON_M, button_M)
                    putSerializable(ARG_BUTTON_Y, button_Y)
                    putSerializable(ARG_BUTTON_DEATH, button_DEATH)
                    putSerializable(ARG_BUTTON_POSITIVE, button_POSITIVE)
                }
            }
    }
}