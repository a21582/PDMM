package com.ipca.formulaworld.ui.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.Car


class CarArrayAdapter(val mList: MutableList<Car>, val itemClickListener: CarFragment): RecyclerView.Adapter<CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bindData(mList[position], itemClickListener)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}