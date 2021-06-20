package com.ipca.formulaworld.ui.classification

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.Pilot
import com.ipca.formulaworld.model.Team
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

/**
 * A simple [Fragment] subclass.
 * Use the [ClassificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassificationFragment : Fragment() {

    private lateinit var pilotsButton: Button
    private lateinit var teamsButton: Button
    private lateinit var pilotsRecyclerView: RecyclerView
    private lateinit var teamsRecyclerView: RecyclerView

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
        return inflater.inflate(R.layout.fragment_classification, container, false)
    }

    private lateinit var pilotsAdapter: ClassificationPilotsArrayAdapter
    private lateinit var teamsAdapter: ClassificationTeamsArrayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // List divider
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.getDrawable(R.drawable.divider)?.let { itemDecoration.setDrawable(it) }

        // Pilots List
        pilotsRecyclerView = view.findViewById<RecyclerView>(R.id.classification_pilots_list)
        pilotsRecyclerView.addItemDecoration(itemDecoration)

        val pilotsLinearLayoutManager = LinearLayoutManager(context)
        pilotsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        pilotsRecyclerView.layoutManager = pilotsLinearLayoutManager
        pilotsRecyclerView.itemAnimator = DefaultItemAnimator()

        // Teams List

        teamsRecyclerView = view.findViewById<RecyclerView>(R.id.classification_teams_list)
        teamsRecyclerView.addItemDecoration(itemDecoration)

        val teamsLinearLayoutManager = LinearLayoutManager(context)
        teamsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        teamsRecyclerView.layoutManager = teamsLinearLayoutManager
        teamsRecyclerView.itemAnimator = DefaultItemAnimator()

        GlobalScope.launch {
            // Update pilots list
            val pilotsValues = mutableListOf<Pilot>()
            val pilots = db?.pilotDao()?.getAll()
            pilots?.forEach {
                Log.d("Pilot", it.name)
                val url = URL(it.photo)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                it.image = bmp

                pilotsValues.add(it)
            }

            // Update teams list
            val teamsValues = mutableListOf<Team>()
            val teams = db?.teamDao()?.getAll()
            teams?.forEach {
                Log.d("Team", it.name)
                val photo = it.photo
                if(photo != null && photo.isNotEmpty() && photo != "null") {
                    val url = URL(photo)
                    val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                    it.image = bmp
                }

                teamsValues.add(it)
            }

            activity?.runOnUiThread {
                pilotsAdapter = ClassificationPilotsArrayAdapter(pilotsValues)
                pilotsRecyclerView.adapter = pilotsAdapter

                teamsAdapter = ClassificationTeamsArrayAdapter(teamsValues)
                teamsRecyclerView.adapter = teamsAdapter
            }
        }

        // Tab buttons click events (switch between lists)
        pilotsButton = view.findViewById<Button>(R.id.classification_pilots_button)
        teamsButton = view.findViewById<Button>(R.id.classification_teams_button)

        showTeamsList()

        pilotsButton.setOnClickListener {
            if(!pilotsButton.isSelected) {
                showPilotsList()
            }
        }

        teamsButton.setOnClickListener {
            if(!teamsButton.isSelected) {
                showTeamsList()
            }
        }
    }

    private fun showPilotsList() {
        pilotsButton.isSelected = true
        teamsButton.isSelected = false

        pilotsRecyclerView.visibility = VISIBLE
        teamsRecyclerView.visibility = INVISIBLE
    }

    private fun showTeamsList() {
        teamsButton.isSelected = true
        pilotsButton .isSelected = false

        teamsRecyclerView.visibility = VISIBLE
        pilotsRecyclerView.visibility = INVISIBLE
    }

}