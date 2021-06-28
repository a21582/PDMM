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
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.Events
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

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
        val dateDay = view.findViewById(com.ipca.formulaworld.R.id.dateDay) as TextView
        val dateMoth = view.findViewById(com.ipca.formulaworld.R.id.dateMoth) as TextView
        val textViewEventDesc = view.findViewById<TextView>(R.id.eventDesc)

        // Fragment title
        activity?.setTitle(R.string.calendar)

        // Show back button
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        calendarView = view.findViewById(R.id.calendar)
        calendarView.setOnDateChangeListener(OnDateChangeListener { calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val date = dayOfMonth.toString() + "−" + (month + 1) + "−" + year

            GlobalScope.launch {
                // Atualizar lista de pilotos
                val data = db!!.eventsDao().findByDate(date)

                activity?.runOnUiThread {
                    if (data != null) {
                        dateDay.text = dayOfMonth.toString()
                        when ((month + 1)) {
                            1 -> {
                                dateMoth.text = "Jan"
                            }
                            2 -> {
                                dateMoth.text = "Fav"
                            }
                            3 -> {
                                dateMoth.text = "Mar"
                            }
                            4 -> {
                                dateMoth.text = "Abr"
                            }
                            5 -> {
                                dateMoth.text = "Mai"
                            }
                            6 -> {
                                dateMoth.text = "Jun"
                            }
                            7 -> {
                                dateMoth.text = "Jul"
                            }
                            8 -> {
                                dateMoth.text = "Ago"
                            }
                            9 -> {
                                dateMoth.text = "Set"
                            }
                            10 -> {
                                dateMoth.text = "Out"
                            }
                            11 -> {
                                dateMoth.text = "Nov"
                            }
                            12 -> {
                                dateMoth.text = "Dez"
                            }
                        }
                        //textViewEventDate.text = data.simpleDate
                        textViewEventDesc.text = data.eventDesc
                    } else {
                        //textViewEventDate.text = ""
                        dateDay.text = ""
                        dateMoth.text = ""
                        textViewEventDesc.text = ""
                    }
                }

            }
        })

    }

}