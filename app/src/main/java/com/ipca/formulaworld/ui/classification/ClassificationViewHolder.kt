package com.ipca.formulaworld.ui.classification

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class ClassificationViewHolder (inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_3, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.list_item_3_title)
    //private var iv: ImageView? = itemView.findViewById(R.id.list_item_3_photo)
    private var cv: CircleImageView? = itemView.findViewById(R.id.list_item_3_circle_photo)
    fun bindData(name: String, photo: Drawable) {
        tv?.text = name
        //val imageFile = File(photo)

            //iv?.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
            //cv?.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))

        cv?.setImageDrawable(photo)



        //iv?.setImageBitmap(photo)
        itemView.setOnClickListener {
            Toast.makeText(parent.context,name, Toast.LENGTH_LONG).show()
        }
    }
}