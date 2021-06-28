package com.ipca.formulaworld.ui.news

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.News


class NewsArrayAdapter(val mList: MutableList<News>, private val onItemClicked: (News) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var newsTitle = mList[position].title
        var newsDescription = mList[position].body
        var newsImage = mList[position].image

        holder.bindData(newsTitle, newsDescription, newsImage)

        holder.button.setOnClickListener {
            onItemClicked(mList[position])
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }
}