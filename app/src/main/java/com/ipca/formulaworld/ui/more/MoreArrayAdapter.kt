package com.ipca.formulaworld.ui.more

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ipca.formulaworld.R
import com.ipca.formulaworld.MenuOption

class MoreArrayAdapter(context: Context, list: MutableList<MenuOption>) : ArrayAdapter<MenuOption>(context, -1) {
    val mList: MutableList<MenuOption> = list

    override fun getItem(position: Int): MenuOption? {
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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_2,parent, false)
            view.tag = MyViewHolder(view)
        }

        vh = view.tag as MyViewHolder

        val pos = getItem(position)

        vh.title?.text = pos?.title
        vh.image?.setImageResource(pos!!.image)

        return view
    }

    private class MyViewHolder(view: View?) {
        val title = view?.findViewById<TextView>(R.id.list_item_2_title)
        val image = view?.findViewById<ImageView>(R.id.list_item_2_photo)
    }
}