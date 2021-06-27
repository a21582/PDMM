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

    /*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: MyViewHolder
        if (convertView != null) {
            view = convertView
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.list_news,parent, false)
            view.tag = MyViewHolder(view)
        }

        vh = view.tag as MyViewHolder

        val pos = getItem(position)

        val url = URL(pos!!.photo)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        vh.image?.setImageBitmap(bmp)

        vh.inform?.text = pos?.title
        //TODO:
        // vh detail?.text = pos?.id


        return view
    }

    private class MyViewHolder(view: View?) {
        val image = view?.findViewById<ImageView>(R.id.list_news_photo)
        val inform = view?.findViewById<TextView>(R.id.list_news_inform)
        val detail = view?.findViewById<Button>(R.id.list_news_details)
    }
     */
}