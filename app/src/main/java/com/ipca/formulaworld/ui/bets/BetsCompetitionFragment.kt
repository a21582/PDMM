package com.ipca.formulaworld.ui.bets

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsCompetition
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_team_bets.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BetsCompetitionFragment : Fragment(){

    val values = mutableListOf<BetsCompetition>()
    private lateinit var mAdapter: BetsCompetitionAdapter

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
        return inflater.inflate(
            com.ipca.formulaworld.R.layout.fragment_competition_bets,
            container,
            false
        )
    }

    @SuppressLint("ResourceType")
    fun onItemClicked(bet: BetsCompetition) {
        val bundle = Bundle()
        bundle.putString("competition", bet.competition.toString())

        val transaction: FragmentTransaction = this.parentFragmentManager.beginTransaction()
        val fragmentTwo = BetsListFragment()
        fragmentTwo.arguments = bundle
        transaction.replace(com.ipca.formulaworld.R.id.fragment_placeholder, fragmentTwo)
        transaction.commit()

    }


    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(com.ipca.formulaworld.R.id.userlist)

        val searchBar = view.findViewById<MaterialSearchBar>(com.ipca.formulaworld.R.id.searchBar)
        searchBar.setHint("Search..")
        searchBar.setSpeechMode(true)


        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        GlobalScope.launch {
            // Atualiza lista de competições
            val data = db!!.betsCompetitionDao().getAll()
            data?.forEach {
                values.add(it)
            }
            activity?.runOnUiThread {
                mAdapter = BetsCompetitionAdapter(
                    values,
                    this@BetsCompetitionFragment
                )
                recyclerView.adapter = mAdapter
            }
        }

        //SEARCHBAR TEXT CHANGE LISTENER
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                if (charSequence.toString() != "") {
                    mAdapter.getFilter().filter(charSequence)
                }


                else
                 recyclerView.adapter = BetsCompetitionAdapter(values, this@BetsCompetitionFragment)

            }

            override fun afterTextChanged(editable: Editable) {
            }


        })

    }

}


