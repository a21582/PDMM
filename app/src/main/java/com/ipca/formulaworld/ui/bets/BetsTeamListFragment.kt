package com.ipca.formulaworld.ui.bets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsTeams
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BetsTeamListFragment : Fragment(), BetsTeamAdapter.OnItemClickListener {
    var inputText: String = ""
    val values = mutableListOf<BetsTeams>()
    private lateinit var mAdapter: BetsTeamAdapter

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
        return inflater.inflate(com.ipca.formulaworld.R.layout.fragment_list_team_bets, container, false)
    }

    override fun onItemClicked(bet: BetsTeams) {

        val bundle = Bundle()
        bundle.putString("name", bet.team)
        bundle.putString("odd", bet.odd)


        val transaction: FragmentTransaction = this.parentFragmentManager.beginTransaction()
        val fragmentTwo = PlaceBets()
        fragmentTwo.arguments = bundle
        transaction.replace(com.ipca.formulaworld.R.id.fragment_placeholder, fragmentTwo)
        transaction.commit()

    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(com.ipca.formulaworld.R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        GlobalScope.launch {
            // Atualizar lista das equpas
            val data = db!!.betsTeamsDao().findByCompetitionId(inputText)
            data?.forEach {
                values.add(it)
            }
            activity?.runOnUiThread {
                mAdapter = BetsTeamAdapter(
                    values,
                    this@BetsTeamListFragment
                )
                recyclerView.adapter = mAdapter
            }
        }


    }



}