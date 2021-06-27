package com.ipca.formulaworld.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "object_id") val objectId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "inform") val inform: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "created") val created: String){
    @Ignore
    var image: Bitmap? = null
}
