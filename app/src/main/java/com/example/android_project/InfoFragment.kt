package com.example.android_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Class InfoFragment
 * Fragment to display information of the application
 */
class InfoFragment : Fragment() {
    /**
     * Creation of the fragment
     * @param   savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    /**
     * Creation the fragment's view
     * @param   inflater
     * @param   container
     * @param   savedInstanceState
     * @return  View | null
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
}