package com.example.android_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_DAILY = "ARG_DAILY"

class DailyListFragment : Fragment() {
    private lateinit var daily: ArrayList<Daily>
    private lateinit var rcvDaily: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            daily = it.getSerializable(ARG_DAILY) as ArrayList<Daily>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_daily_list, container, false)

        this.rcvDaily = rootView.findViewById(R.id.f_daily_list_rcv_daily)
        this.rcvDaily.adapter = DailyAdapter(daily)
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvDaily.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvDaily.addItemDecoration(dividerItemDecoration)

        return rootView;
    }

    companion object {
        @JvmStatic
        fun newInstance(daily: List<Daily>) =
            DailyListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_DAILY, ArrayList(daily))
                }
            }
    }
}