package com.ipca.formulaworld.ui.calendar

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ipca.formulaworld.ui.bets.BetsPlayersListFragment
import com.ipca.formulaworld.ui.bets.BetsTeamListFragment

class EventsCalendarFragment : Fragment() {
    private lateinit var eventsButton: Button
    private lateinit var calendarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.ipca.formulaworld.R.layout.fragment_calendar_events, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Tab buttons click events (switch between lists)
        eventsButton = view.findViewById<Button>(com.ipca.formulaworld.R.id.events_button)
        calendarButton = view.findViewById<Button>(com.ipca.formulaworld.R.id.data_button)

        showEvents()

        eventsButton.setOnClickListener {
            if(!eventsButton.isSelected) {
                showEvents()
            }
        }

        calendarButton.setOnClickListener {
            if(!calendarButton.isSelected) {
                showCalendar()
            }
        }
    }

    fun showEvents(){
        eventsButton.isSelected = true
        calendarButton.isSelected = false

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val fragmentTwo = EventsFragment()
        transaction.replace(com.ipca.formulaworld.R.id.fragment_list, fragmentTwo)
        transaction.commit()
    }

    fun showCalendar(){
        eventsButton.isSelected = false
        calendarButton.isSelected = true
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val fragmentTwo = CalendarFragment()
        transaction.replace(com.ipca.formulaworld.R.id.fragment_list, fragmentTwo)
        transaction.commit()
    }
}