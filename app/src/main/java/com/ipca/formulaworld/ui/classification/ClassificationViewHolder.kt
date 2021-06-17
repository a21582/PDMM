package com.ipca.formulaworld.ui.classification

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ipca.formulaworld.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class ClassificationViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_3, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.list_item_3_title)
    //private var iv: ImageView? = itemView.findViewById(R.id.list_item_3_photo)
    private var cv: ImageView? = itemView.findViewById(R.id.list_item_3_circle_photo)

    fun bindData(name: String, photo: Bitmap?) {
        tv?.text = name
        cv?.setImageBitmap(photo)
        //val imageFile = File(photo)

            //iv?.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
            //cv?.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))

//        val uri: Uri = photo as Uri
//        val uri: Uri = Uri.parse(photo.toString())

//        val r = Runnable {
//            val url = URL(photo)
//            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

//            cv?.setImageBitmap(photo)
//        }
//        val t = Thread(r)
//        t.start()

//        cv?.setImageURI(uri)

        //iv?.setImageBitmap(photo)
        itemView.setOnClickListener {
            Toast.makeText(parent.context, name, Toast.LENGTH_LONG).show()
        }
    }
}