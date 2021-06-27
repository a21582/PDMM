package com.ipca.formulaworld.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "events_table")
data class Events(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "object_id") val objectId: String,
    @ColumnInfo(name = "eventDay") val eventDay: String,
    @ColumnInfo(name = "eventDesc") val eventDesc: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String
    ) {
    @Ignore
    var image2: Bitmap? = null
}
