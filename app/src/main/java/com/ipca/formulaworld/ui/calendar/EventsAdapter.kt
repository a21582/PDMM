package com.ipca.formulaworld.ui.calendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.Events


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
            val dateDay = itemView.findViewById(com.ipca.formulaworld.R.id.dateDay) as TextView
            val dateMoth = itemView.findViewById(com.ipca.formulaworld.R.id.dateMoth) as TextView

            val lstValues: List<String> = event.eventDay.split("−").map { it -> it.trim() }

            title.text = event.title
            desc.text = event.eventDesc
            dateDay.text = lstValues[0]
            when (lstValues[1]) {
                1.toString() -> {
                    dateMoth.text = "Jan"
                }
                2.toString() -> {
                    dateMoth.text = "Fev"
                }
                3.toString() -> {
                    dateMoth.text = "Mar"
                }
                4.toString() -> {
                    dateMoth.text = "Abr"
                }
                5.toString() -> {
                    dateMoth.text = "Mai"
                }
                6.toString() -> {
                    dateMoth.text = "Jun"
                }
                7.toString() -> {
                    dateMoth.text = "Jul"
                }
                8.toString() -> {
                    dateMoth.text = "Ago"
                }
                9.toString() -> {
                    dateMoth.text = "Set"
                }
                10.toString() -> {
                    dateMoth.text = "Out"
                }
                11.toString() -> {
                    dateMoth.text = "Nov"
                }
                12.toString() -> {
                    dateMoth.text = "Dez"
                }
            }

            image!!.setImageBitmap(event.image2)
        }


    }

}