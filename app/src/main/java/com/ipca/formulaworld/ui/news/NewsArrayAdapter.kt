package com.ipca.formulaworld.ui.news


import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import com.ipca.formulaworld.R
import com.ipca.formulaworld.MenuOption
import com.ipca.formulaworld.model.News
import java.net.URL

class NewsArrayAdapter(context: Context, list: MutableList<News>) : ArrayAdapter<News>(context, -1) {
    val mList: MutableList<News> = list

    override fun getItem(position: Int): News? {
        return mList.get(position)
    }
    override fun getCount(): Int {
        return mList.count()
    }

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
}