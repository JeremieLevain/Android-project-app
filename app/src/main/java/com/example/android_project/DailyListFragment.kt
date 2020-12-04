package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_DAILY = "ARG_DAILY"

/**
 * Class DailyListFragment
 * Fragment which display the list of daily with a RecyclerView
 * @property    daily       List of data
 * @property    rcvDaily    RecyclerView
 */
class DailyListFragment : Fragment(), DailyClickListener  {
    private lateinit var daily: ArrayList<Daily>
    private lateinit var rcvDaily: RecyclerView

    /**
     * Creation of the fragment
     * @param   savedInstanceState  Data received from MainActivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daily = arguments!!.getSerializable(ARG_DAILY) as ArrayList<Daily>
    }

    /**
     * Creation of the view
     * @param   inflater
     * @param   container
     * @param   savedInstanceState
     * @return  View | null
     */
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_daily_list, container, false)

        // Setting of the RecyclerView
        this.rcvDaily = rootView.findViewById(R.id.f_daily_list_rcv_daily)
        this.rcvDaily.adapter = DailyAdapter(daily, this)
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvDaily.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvDaily.addItemDecoration(dividerItemDecoration)

        return rootView
    }

    /**
     * Exception method for click events
     * @param   view    Parent activity
     * @param   daily   Daily with detail to display
     */
    override fun onDailyClickListener(view: View, daily: Daily) {
        val bundle = Bundle()

        // Send an object Daily to DetailDailyActivity
        bundle.putSerializable("daily", daily)
        val intent = Intent(context, DetailDailyActivity::class.java)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    /**
     * Create a new instance of DailyListFragment
     * @param   daily   List of Daily
     * @return  DailyListFragment
     */
    companion object {
        @JvmStatic
        fun newInstance(daily: List<Daily>): DailyListFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARG_DAILY, ArrayList(daily))

            val dailyListFragment = DailyListFragment()
            dailyListFragment.arguments = bundle

            return dailyListFragment
        }
    }
}
