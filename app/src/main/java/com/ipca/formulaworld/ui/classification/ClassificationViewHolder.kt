package com.ipca.formulaworld.ui.classification

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R

class ClassificationViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_classification, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.list_item_classification_title)
    private var cv: ImageView? = itemView.findViewById(R.id.list_item_classification_photo)

    fun bindData(name: String, photo: Bitmap?) {
        tv?.text = name
        cv?.setImageBitmap(photo)

        itemView.setOnClickListener {
            Toast.makeText(parent.context, name, Toast.LENGTH_LONG).show()
        }
    }
}