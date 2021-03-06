package com.ipca.formulaworld.ui.bets


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.BetsTeams

class BetsTeamAdapter(var values: MutableList<BetsTeams>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BetsTeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.ipca.formulaworld.R.layout.list_item_6, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(values[position], itemClickListener)
    }

    override fun getItemCount(): Int = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        fun bindItems(bet: BetsTeams, clickListener: OnItemClickListener) {
            val textViewName = itemView.findViewById(com.ipca.formulaworld.R.id.team) as TextView
            val textViewOdd  = itemView.findViewById(com.ipca.formulaworld.R.id.odd) as TextView
            textViewName.text = bet.team
            textViewOdd.text = bet.odd
            itemView.setOnClickListener {
                clickListener.onItemClicked(bet)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                //listener.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener{
        fun onItemClicked(bet: BetsTeams)
    }


}