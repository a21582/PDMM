package com.ipca.formulaworld.ui.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.ipca.formulaworld.R
import com.ipca.formulaworld.MenuOption

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerview)

        val listView = view.findViewById<ListView>(R.id.more_listview)

        val options = mutableListOf<MenuOption>(
                MenuOption("Estatísticas", R.drawable.ic_statistics),
                MenuOption("História", R.drawable.ic_history),
                MenuOption("Fórum", R.drawable.ic_discussion),
        )
        val adapter = MoreArrayAdapter(view.context, options)
        listView.adapter = adapter

//        recyclerView.adapter = MoreAdapterRec(listOf(MenuOption("Teste1", android.R.drawable.star_on)))
//        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}