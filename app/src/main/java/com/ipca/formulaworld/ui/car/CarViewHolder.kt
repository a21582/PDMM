package com.ipca.formulaworld.ui.car

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R

class CarViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_car, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.list_item_car_title)
    private var cv: ImageView? = itemView.findViewById(R.id.list_item_car_photo)

    fun bindData(name: String, photo: Bitmap?) {
        tv?.text = name
        cv?.setImageBitmap(photo)

        itemView.setOnClickListener {
            Toast.makeText(parent.context, name, Toast.LENGTH_LONG).show()
        }
    }
}