package com.ipca.formulaworld.ui.classification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R
import com.ipca.formulaworld.model.pilot

/**
 * A simple [Fragment] subclass.
 * Use the [ClassificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassificationFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgVerstappen = context?.resources?.getDrawable(R.drawable.verstappen)
        val imgHamilton = context?.resources?.getDrawable(R.drawable.hamilton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.classification_RecycleView)
        recyclerView.adapter = ClassificationArrayAdapter(mutableListOf<pilot>(
            pilot("Verstappen",imgVerstappen!!,1,1999),
            pilot("Hamilton",imgHamilton!!,2,1999)))
        //recyclerView.layoutManager = LinearLayoutManager(this) ///storage/emulated/0/Pictures/IMG_20210521_000128.jpg
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        val pilotsButton = view.findViewById<Button>(R.id.classification_pilots_button)
        pilotsButton.setOnClickListener {
            Log.d("Teste", "Teste")
        }
    }

}