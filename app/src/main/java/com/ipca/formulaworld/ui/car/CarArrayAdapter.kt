package com.ipca.formulaworld.ui.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.Car


class CarArrayAdapter( val mList: MutableList<Car>): RecyclerView.Adapter<CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        var carName = mList[position].name
        var carImage = mList[position].image

        holder.bindData(carName, carImage)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}