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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.Pilot
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

    private val db by lazy {
        activity?.let {
            Room.databaseBuilder(
                it.applicationContext,
                MyDatabase::class.java, "formulaworld.db"
            ).build()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classification, container, false)
    }

    private lateinit var adapter: ClassificationArrayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pilots List
        val imgVerstappen = context?.resources?.getDrawable(R.drawable.verstappen)
        val imgHamilton = context?.resources?.getDrawable(R.drawable.hamilton)
        pilotsRecyclerView = view.findViewById<RecyclerView>(R.id.classification_pilots_list)

        GlobalScope.launch {
            // Atualizar lista de pilotos
            val values = mutableListOf<Pilot>()
            val data = db?.pilotDao()?.getAll()
            data?.forEach {
                val url = URL(it.photo)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                it.image = bmp

                values.add(it)
//                adapter.mList.add(it)
            }

            activity?.runOnUiThread {
                adapter = ClassificationArrayAdapter(values)
                pilotsRecyclerView.adapter = adapter
            }
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        pilotsRecyclerView.layoutManager = linearLayoutManager

//        val firebaseDB = FirebaseFirestore.getInstance()

        // Teams List

        // Tab buttons click events (switch between lists)
        pilotsButton = view.findViewById<Button>(R.id.classification_pilots_button)
        teamsButton = view.findViewById<Button>(R.id.classification_teams_button)

        showPilotsList()

        pilotsButton.setOnClickListener {
            Log.d("Teste", "Teste1")
            if(!pilotsButton.isSelected) {
                showPilotsList()
            }
        }

        teamsButton.setOnClickListener {
            Log.d("Teste", "Teste2")
            if(!teamsButton.isSelected) {
                showTeamsList()
            }
        }
    }

    private fun showPilotsList() {
        pilotsButton.isSelected = true
        teamsButton.isSelected = false

        pilotsRecyclerView.visibility = VISIBLE
    }

    private fun showTeamsList() {
        teamsButton.isSelected = true
        pilotsButton .isSelected = false

        pilotsRecyclerView.visibility = INVISIBLE
    }

}