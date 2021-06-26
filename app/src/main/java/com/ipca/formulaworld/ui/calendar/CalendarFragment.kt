package com.ipca.formulaworld.ui.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsPlayers
import com.ipca.formulaworld.model.Events
import com.ipca.formulaworld.ui.bets.BetsPlayerAdapter
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CalendarFragment : Fragment() {
    lateinit var calendarView: CalendarView
    var values = mutableListOf<Events>()

    private val db by lazy {
        activity?.let {
            Room.databaseBuilder(
                it.applicationContext,
                MyDatabase::class.java, "formulaworld.db"
            ).build()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textViewEventDate = view.findViewById<TextView>(R.id.eventDay)
        val textViewEventDesc = view.findViewById<TextView>(R.id.eventDesc)

        calendarView = view.findViewById(R.id.calendar)
        calendarView.setOnDateChangeListener(OnDateChangeListener { calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val date = dayOfMonth.toString() + "−" + (month + 1) + "−" + year
            Log.d("data", date)

            GlobalScope.launch {
                // Atualizar lista de pilotos
                val data = db!!.eventsDao().findByDate(date)

                activity?.runOnUiThread {
                    if (data != null) {
                        textViewEventDate.text = data.simpleDate
                        textViewEventDesc.text = data.eventDesc
                    } else {
                        textViewEventDate.text = ""
                        textViewEventDesc.text = ""
                    }
                }

            }
        })

    }

}