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
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.MenuOption
import com.ipca.formulaworld.R
import com.ipca.formulaworld.ui.calendar.CalendarFragment
import com.ipca.formulaworld.ui.calendar.EventsCalendarFragment
import com.ipca.formulaworld.ui.calendar.EventsFragment
import com.ipca.formulaworld.ui.car.CarFragment
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

        // Fragment title
        activity?.setTitle(R.string.title_categories)

        // Remove back button
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val listView = view.findViewById<ListView>(R.id.categories_listview)
        listView.divider = null;

        val act = (activity as MainActivity)

        val options = mutableListOf(
            MenuOption(act.baseContext.resources.getString(R.string.title_classification), R.drawable.ic_statistics),
            MenuOption(act.baseContext.resources.getString(R.string.cars), R.drawable.ic_racing_car),
            MenuOption(act.baseContext.resources.getString(R.string.calendar), R.drawable.ic_calendar),
        )
        val adapter = CategoriesArrayAdapter(view.context, options)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(0)

            when(position) {
                0 -> {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, ClassificationFragment())?.addToBackStack(null)
                    ft?.commit()
                }
                1 -> {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, CarFragment())?.addToBackStack(null)
                    ft?.commit()
                }
                2 -> {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, EventsCalendarFragment())?.addToBackStack(null)
                    ft?.commit()
                }
            }
        }
    }


}