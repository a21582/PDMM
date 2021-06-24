package com.ipca.formulaworld.ui.bets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.BetsCompetition

class BetsCompetitionAdapter (var values: MutableList<BetsCompetition>, val itemClickListener: BetsCompetitionFragment) :
    RecyclerView.Adapter<BetsCompetitionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetsCompetitionAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(inflater, parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(values[position], itemClickListener)


    }

    override fun getItemCount(): Int = values.size


    fun getFilter(): Filter {

        return object : Filter() {

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: Filter.FilterResults
            ) {
                values = filterResults.values as ArrayList<BetsCompetition>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty())
                    values
                else
                    values.filter {
                        it.competition?.toLowerCase()!!.contains(queryString)
                    }
                return filterResults
            }



        }

    }

    inner class ViewHolder(inflater: LayoutInflater, private val parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(com.ipca.formulaworld.R.layout.list_item_5, parent, false)), View.OnClickListener
    {

        fun bindItems(bet: BetsCompetition, clickListener: BetsCompetitionFragment) {
            val textViewCompetition = itemView.findViewById(com.ipca.formulaworld.R.id.competition) as TextView

            textViewCompetition.text = bet.competition.toString()
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


}