package com.ipca.formulaworld.ui.categories


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import com.ipca.formulaworld.MenuOption
import com.ipca.formulaworld.R
import com.ipca.formulaworld.ui.calendar.CalendarFragment
import com.ipca.formulaworld.ui.calendar.EventsCalendarFragment
import com.ipca.formulaworld.ui.calendar.EventsFragment
import com.ipca.formulaworld.ui.classification.ClassificationFragment

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.categories_listview)
        listView.divider = null;

        val options = mutableListOf<MenuOption>(
            MenuOption("Classificação", R.drawable.ic_statistics),
            MenuOption("Carros", R.drawable.ic_racing_car),
            MenuOption("Pilotos", R.drawable.ic_helmet),
            MenuOption("Equipas", R.drawable.ic_group),
            MenuOption("Calendário", R.drawable.ic_calendar),
        )
        val adapter = CategoriesArrayAdapter(view.context, options)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            Log.d("Teste", "Click")
            adapter.getItem(0)

            when(position) {
                0 -> {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, ClassificationFragment())
                    ft?.commit()
                }
                1 -> {
                    Log.d("Teste", "Pos 1")
                }
                2 -> {
                    Log.d("Teste", "Pos 0")
                }
                3 -> {
                    Log.d("Teste", "Pos 1")
                }
                4 -> {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, EventsCalendarFragment())
                    ft?.commit()
                    Log.d("Teste", "Pos 0")
                }
            }
        }
    }


}