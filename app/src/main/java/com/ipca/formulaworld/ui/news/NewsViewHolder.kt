package com.ipca.formulaworld.ui.news

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R

class NewsViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_news, parent, false)) {

    private var titleTextView: TextView? = itemView.findViewById(R.id.list_news_title)
    private var descriptionTextView: TextView? = itemView.findViewById(R.id.list_news_description)
    private var imageView: ImageView? = itemView.findViewById(R.id.list_news_photo)
    var button: Button = itemView.findViewById(R.id.list_news_details)

    fun bindData(title: String, description: String, photo: Bitmap?) {
        titleTextView?.text = title
        descriptionTextView?.text = description
        imageView?.setImageBitmap(photo)
    }
}