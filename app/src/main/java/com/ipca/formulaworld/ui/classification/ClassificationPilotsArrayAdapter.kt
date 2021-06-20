package com.ipca.formulaworld.ui.classification

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R
import com.ipca.formulaworld.model.Pilot
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File


class ClassificationPilotsArrayAdapter(val mList: MutableList<Pilot>) : RecyclerView.Adapter<ClassificationViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ClassificationViewHolder(inflater, parent)
    }

     override fun onBindViewHolder(holder: ClassificationViewHolder, position: Int) {
         var pilotName = mList[position].name
         var pilotImage = mList[position].image

        holder.bindData(pilotName, pilotImage)
    }
     override fun getItemCount(): Int {
        return mList.size
    }

    /*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView != null) {
            view = convertView
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_3,parent, false)
            view.tag = MyViewHolder(view)
        }

        val vh: MyViewHolder = view.tag as MyViewHolder

        val pos = getItem(position)

        vh.title?.text = pos?.name
//        vh.image?.setImageResource(pos!!.image)

        return view
    }

    private class MyViewHolder(view: View?) {
        val title = view?.findViewById<TextView>(R.id.list_item_3_title)
        val photo = view?.findViewById<CircleImageView>(R.id.list_item_3_circle_photo)
    }
     */
}


