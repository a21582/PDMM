package com.ipca.formulaworld.ui.news

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.News
import com.ipca.formulaworld.ui.details.DetailsFragment
import com.ipca.formulaworld.utils.isNetworkAvailable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsArrayAdapter

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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment title
        activity?.setTitle(R.string.title_home)

        // Remove back button
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // List divider
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.getDrawable(R.drawable.divider)?.let { itemDecoration.setDrawable(it) }

        // News List
        newsRecyclerView = view.findViewById(R.id.news_list)
        newsRecyclerView.addItemDecoration(itemDecoration)

        val newsLinearLayoutManager = LinearLayoutManager(context)
        newsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        newsRecyclerView.layoutManager = newsLinearLayoutManager
        newsRecyclerView.itemAnimator = DefaultItemAnimator()

        GlobalScope.launch {
            // Update news list
            val newsValues = mutableListOf<News>()
            val news = db?.newsDao()?.getAllNewsByCreated()
            news?.forEach {
                activity?.let { it1 ->
                    if(isNetworkAvailable(it1.applicationContext) ) {
                        val url = URL(it.photo)
                        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                        it.image = bmp
                    }
                }

                newsValues.add(it)
            }

            activity?.runOnUiThread {
                newsAdapter = NewsArrayAdapter(newsValues) {
                    val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.fragment_placeholder, DetailsFragment.newInstance(it.title, it.body, it.photo))?.addToBackStack(null)
                    ft?.commit()
                }
                newsRecyclerView.adapter = newsAdapter
            }
        }
    }
}