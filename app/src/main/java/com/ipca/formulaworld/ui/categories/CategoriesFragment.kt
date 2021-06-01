package com.ipca.formulaworld.ui.categories


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.pilot
import com.ipca.formulaworld.ui.classification.ClassificationArrayAdapter
import com.ipca.formulaworld.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

//        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerview)
        val imgVerstappen = context?.resources?.getDrawable(R.drawable.verstappen)
        val imgHamilton = context?.resources?.getDrawable(R.drawable.hamilton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_RecycleView)
        recyclerView.adapter = ClassificationArrayAdapter(mutableListOf<pilot>(
            pilot("Verstappen",imgVerstappen!!,1,1999),
            pilot("Hamilton",imgHamilton!!,2,1999)))
        //recyclerView.layoutManager = LinearLayoutManager(this) ///storage/emulated/0/Pictures/IMG_20210521_000128.jpg
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

//        recyclerView.adapter = MoreAdapterRec(listOf(MenuOption("Teste1", android.R.drawable.star_on)))
//        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }


}