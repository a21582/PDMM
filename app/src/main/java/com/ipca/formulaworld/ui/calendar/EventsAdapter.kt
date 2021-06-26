package com.ipca.formulaworld.ui.calendar

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.Events
import java.net.URL


class EventsAdapter(var values: MutableList<Events>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.ipca.formulaworld.R.layout.list_events, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("WrongViewCast")
        fun bindItems(event: Events) {

            val image = itemView.findViewById(com.ipca.formulaworld.R.id.idImage) as ImageView?
            val title = itemView.findViewById(com.ipca.formulaworld.R.id.idTitle) as TextView
            val desc = itemView.findViewById(com.ipca.formulaworld.R.id.idDesc) as TextView
            val date = itemView.findViewById(com.ipca.formulaworld.R.id.date) as TextView

            //courseIV = bet.image
            title.text = event.title
            desc.text = event.eventDesc
            date.text = event.simpleDate
            /*
            val url = URL(event.image)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

             */

            image!!.setImageBitmap(event.image2)
        }


    }

}