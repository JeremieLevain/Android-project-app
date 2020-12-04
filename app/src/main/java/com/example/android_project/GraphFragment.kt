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
private const val ARG_BUTTON_M = "ARG_BUTTON_M"
private const val ARG_BUTTON_Y = "ARG_BUTTON_Y"
private const val ARG_BUTTON_DEATH = "ARG_BUTTON_DEATH"
private const val ARG_BUTTON_POSITIVE = "ARG_BUTTON_POSITIVE"

/**
 * Class GraphFragment
 * Fragment displaying a graph of current data on Covid-19 in USA
 * @property    data
 * @property    buttonMa3D
 * @property    buttonM
 * @property    buttonY
 * @property    buttonDEATH
 * @property    buttonPOSITIVE
 */
class GraphFragment : Fragment() {
    private lateinit var data: ArrayList<Daily>
    private var buttonMa3D = false
    private var buttonM = false
    private var buttonY = false
    private var buttonDEATH = false
    private var buttonPOSITIVE = false

    /**
     * Creation of the fragment
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable(ARG_DATA) as ArrayList<Daily>
            buttonMa3D = it.getSerializable(ARG_BUTTON_MA_3D) as Boolean
            buttonM = it.getSerializable(ARG_BUTTON_M) as Boolean
            buttonY = it.getSerializable(ARG_BUTTON_Y) as Boolean
            buttonDEATH = it.getSerializable(ARG_BUTTON_DEATH) as Boolean
            buttonPOSITIVE = it.getSerializable(ARG_BUTTON_POSITIVE) as Boolean
        }
    }

    /**
     * Creation of the fragment's view
     * @param   inflater
     * @param   container
     * @param   savedInstanceState
     * @return  View | null
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_graph, container, false)
        val graph = rootView.findViewById(R.id.graph) as GraphView
        val switch = rootView.findViewById(R.id.f_graph_switch_MA) as Switch

        val currentDate = getCurrentDate()
        val previousMonthDate = getPreviousMonth()

        // Display data of the current month or 2020
        drawGraph(graph, previousMonthDate, currentDate)
        switch.isChecked = buttonMa3D

        return rootView
    }

    /**
     * Get the current date
     * @return  Int
     */
    private fun getCurrentDate(): Int {
        return ArrayList(data.sortedByDescending { daily : Daily -> daily.date })[0].date
    }

    /**
     * Get the date 30 days ago
     * @return  Int
     */
    private fun getPreviousMonth() : Int {
        return ArrayList(data.sortedByDescending { daily : Daily -> daily.date })[31].date
    }

    /**
     * Get all daily in a range of dates
     * @param   date1
     * @param   date2
     * @return  List<Daily>
     */
    private fun getDailyBetweenDate(date1: Int, date2: Int): List<Daily> {
        return ArrayList(this.data).filter { daily: Daily ->
            daily.date in date1..date2
        }.sortedByDescending { daily : Daily -> daily.date  }.reversed()
    }

    /**
     * Calculate the average in three days or not
     * @param   tab
     * @param   i
     */
    private fun average(tab: List<Daily>, i: Int): Double {
        var result: Double
        // Return the average on three day of death or positive cases
        if (buttonMa3D) {
            result = (tab[i].day_death + tab[i + 1].day_death + tab[i + 2].day_death) / 3.toDouble()
            if (buttonPOSITIVE)
                result = (tab[i].day_positive + tab[i + 1].day_positive + tab[i + 2].day_positive) / 3.toDouble()
            return result
        }
        // Return the number of death or positive cases each day
        result = tab[i].day_death.toDouble()
        if (buttonPOSITIVE)
            result = tab[i].day_positive.toDouble()
        return result
    }

    /**
     * Draw the graph in function of the different parameters
     * @param   graph
     * @param   previousMonthDate
     * @param   currentDate
     */
    private fun drawGraph(graph: GraphView, previousMonthDate: Int, currentDate: Int) {
        // Init the x-axis list and xmax
        var tab1 = getDailyBetweenDate(previousMonthDate, currentDate)
        var xmax = 35.0
        if (buttonY) {
            tab1 = getDailyBetweenDate(20200101, currentDate)
            xmax = 350.0
        }
        // Init the title of the graph and ymax
        var ymax = 2800.0
        graph.title = "DEATH"
        if (buttonPOSITIVE) {
            ymax = 250000.0
            graph.title = "POSITIVE"
        }
        // Init the number of abscissa
        var size = tab1.size
        if (buttonMa3D)
            size -= 3

        // Create points of the graph
        val tab2 = Array(size) { i: Int -> DataPoint(i.toDouble(), average(tab1, i)) }

        // Make the graph
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(tab2)
        setAxis(graph, xmax, ymax)

        // Display the graph
        graph.addSeries(series)
    }

    /**
     * Set the parameters of the graph
     * @param   graph
     * @param   xmax
     * @param   ymax
     */
    private fun setAxis(graph: GraphView, xmax: Double, ymax: Double) {
        graph.viewport.setMinX(1.0)
        graph.viewport.setMinY(1.0)
        graph.viewport.setMaxX(xmax)
        graph.viewport.setMaxY(ymax)
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.isXAxisBoundsManual = true
    }

    /**
     * Create a new instance of GraphFragment
     * @property    data
     * @property    buttonMa3D
     * @property    buttonW
     * @property    buttonM
     * @property    buttonY
     * @property    buttonDEATH
     * @property    buttonPOSITIVE
     * @return  GraphFragment
     */
    companion object {
        @JvmStatic
        fun newInstance(data: List<Daily>,
                        buttonMa3D : Boolean,
                        buttonM : Boolean,
                        buttonY : Boolean,
                        buttonDEATH : Boolean,
                        buttonPOSITIVE : Boolean) = GraphFragment().apply {
                            arguments = Bundle().apply {
                                putSerializable(ARG_DATA, ArrayList(data))
                                putSerializable(ARG_BUTTON_MA_3D, buttonMa3D)
                                putSerializable(ARG_BUTTON_M, buttonM)
                                putSerializable(ARG_BUTTON_Y, buttonY)
                                putSerializable(ARG_BUTTON_DEATH, buttonDEATH)
                                putSerializable(ARG_BUTTON_POSITIVE, buttonPOSITIVE)
                            }
                        }
    }
}