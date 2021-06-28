package com.ipca.formulaworld.ui.car

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R
import com.ipca.formulaworld.model.Car
import com.ipca.formulaworld.ui.bets.BetsCompetitionFragment

class CarViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_car, parent, false)), View.OnClickListener {
    private var tv: TextView? = itemView.findViewById(R.id.list_item_car_title)
    private var cv: ImageView? = itemView.findViewById(R.id.list_item_car_photo)

    fun bindData(car: Car, clickListener: CarFragment) {
        tv?.text = car.name
        cv?.setImageBitmap(car.image)

        itemView.setOnClickListener {
            clickListener.onItemClicked(car)
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