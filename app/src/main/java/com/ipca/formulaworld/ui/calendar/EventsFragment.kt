package com.ipca.formulaworld.ui.calendar

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsPlayers
import com.ipca.formulaworld.model.Events
import com.ipca.formulaworld.ui.bets.BetsPlayerAdapter
import com.ipca.formulaworld.ui.bets.PlaceBets
import com.ipca.formulaworld.utils.isNetworkAvailable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class EventsFragment: Fragment() {
    var values = mutableListOf<Events>()
    lateinit var mAdapter: EventsAdapter

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
        return inflater.inflate(com.ipca.formulaworld.R.layout.fragment_events, container, false)
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(com.ipca.formulaworld.R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        GlobalScope.launch {
            // Atualizar lista de pilotos
            val data = db!!.eventsDao().getAll()
            data?.forEach {
                activity?.let { it1 ->
                    if(isNetworkAvailable(it1.applicationContext) ) {
                        val url = URL(it.image)
                        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                        it.image2 = bmp
                    }
                }

                values.add(it)
            }
            activity?.runOnUiThread {
                mAdapter = EventsAdapter(values)
                recyclerView.adapter = mAdapter
            }
        }

    }


}