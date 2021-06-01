package com.ipca.formulaworld.ui.classification

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.model.pilot
import java.io.File


class ClassificationArrayAdapter(private val mList: MutableList<pilot>) : RecyclerView.Adapter<ClassificationViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ClassificationViewHolder(inflater, parent)
    }



     override fun onBindViewHolder(holder: ClassificationViewHolder, position: Int) {
         var pilotName= mList[position].name
         var pilotImagePath = mList[position].photo

        holder.bindData(pilotName,pilotImagePath)
    }
     override fun getItemCount(): Int {
        return mList.size
    }





}


