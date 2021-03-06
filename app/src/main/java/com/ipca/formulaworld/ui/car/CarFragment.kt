package com.ipca.formulaworld.ui.car

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.BetsCompetition
import com.ipca.formulaworld.model.Car
import com.ipca.formulaworld.ui.bets.BetsListFragment
import com.ipca.formulaworld.ui.details.DetailsFragment
import com.ipca.formulaworld.utils.isNetworkAvailable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL


/**
 * A simple [Fragment] subclass.
 * Use the [CarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarFragment : Fragment() {
    private lateinit var carsButton: Button
    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var carsAdapter: CarArrayAdapter

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
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    @SuppressLint("ResourceType")
    fun onItemClicked(car: Car) {

        val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.fragment_placeholder, DetailsFragment.newInstance(car.name, "", car.photo))?.addToBackStack(null)
        ft?.commit()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment title
        activity?.setTitle(R.string.cars)

        // Show back button
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // List divider
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.getDrawable(R.drawable.divider)?.let { itemDecoration.setDrawable(it) }

        // Pilots List
        carsRecyclerView = view.findViewById<RecyclerView>(R.id.classification_cars_list)
        carsRecyclerView.addItemDecoration(itemDecoration)

        val pilotsLinearLayoutManager = LinearLayoutManager(context)
        pilotsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        carsRecyclerView.layoutManager = pilotsLinearLayoutManager
        carsRecyclerView.itemAnimator = DefaultItemAnimator()

        // Teams List



        GlobalScope.launch {
            // Update pilots list
            val carsValues = mutableListOf<Car>()
            val cars = db?.carDao()?.getAllOrderByCar()
            cars?.forEach {
                activity?.let { it1 ->
                    if (isNetworkAvailable(it1.applicationContext)) {
                        val url = URL(it.photo)
                        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                        it.image = bmp
                    }
                }

                carsValues.add(it)
            }

            // Update teams list


            activity?.runOnUiThread {
                carsAdapter = CarArrayAdapter(carsValues, this@CarFragment)
                carsRecyclerView.adapter = carsAdapter


            }
        }
      // carsButton = view.findViewById<Button>(R.id.classification_cars_button)

        //showCarsList()
        carsRecyclerView.visibility = View.VISIBLE
       /* carsButton.setOnClickListener {
            if(!carsButton.isSelected) {
                showCarsList()
            }
        } */



    }
    private fun showCarsList() {
        carsButton.isSelected = true


        carsRecyclerView.visibility = View.VISIBLE

    }
}