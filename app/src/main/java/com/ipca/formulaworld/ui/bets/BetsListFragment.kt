package com.ipca.formulaworld.ui.bets

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsCompetition

class BetsListFragment : Fragment() {
    var inputText: String = ""
    val values = mutableListOf<BetsCompetition>()

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
        inputText = arguments?.getString("competition").toString()
        // Inflate the layout for this fragment
        return inflater.inflate(com.ipca.formulaworld.R.layout.fragment_list_bets, container, false)
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(com.ipca.formulaworld.R.id.spinner)
        if (spinner != null) {
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    R.layout.simple_spinner_item,
                    listOf("Teams", "Players")
                )
            }
            adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                val bundle = Bundle()
                bundle.putString("competition", inputText)

                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                val fragmentTwo = BetsTeamListFragment()
                fragmentTwo.arguments = bundle
                transaction.replace(com.ipca.formulaworld.R.id.fragment_list_odds, fragmentTwo)
                transaction.commit()

                when(position) {
                    0 -> {
                        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                        val fragmentTwo = BetsTeamListFragment()
                        fragmentTwo.arguments = bundle
                        transaction.replace(com.ipca.formulaworld.R.id.fragment_list_odds, fragmentTwo)
                        transaction.commit()

                    }
                    1 -> {
                        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                        val fragmentTwo = BetsPlayersListFragment()
                        fragmentTwo.arguments = bundle
                        transaction.replace(com.ipca.formulaworld.R.id.fragment_list_odds, fragmentTwo)
                        transaction.commit()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }

}