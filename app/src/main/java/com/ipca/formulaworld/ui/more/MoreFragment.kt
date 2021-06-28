package com.ipca.formulaworld.ui.more

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ipca.formulaworld.MapActivity
import com.ipca.formulaworld.MenuOption
import com.ipca.formulaworld.R
import com.ipca.formulaworld.ui.Map.MapFragment
import com.ipca.formulaworld.ui.Map.MyLocation

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

        activity?.setTitle(R.string.title_more)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerview)

        val listView = view.findViewById<ListView>(R.id.more_listview)
        listView.divider = null;

        val options = mutableListOf<MenuOption>(
            MenuOption("Grand Prix Circuits", R.drawable.ic_location),
            MenuOption("My Location", R.drawable.ic_baseline_my_location_24),
            MenuOption("Fórum", R.drawable.ic_discussion),
        )
        val adapter = MoreArrayAdapter(view.context, options)
        listView.adapter = adapter


        listView.setOnItemClickListener { parent, view, position, id ->

            adapter.getItem(0)

            when (position) {
                0 -> {

                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, MapFragment())

                    ft?.commit()
                }
                1 -> {

                    val intent = Intent(activity, MyLocation::class.java)
                    startActivity(intent)
                }
                2 -> {
                    Log.d("Teste", "Pos 0")
                }

            }
        }
    }
}