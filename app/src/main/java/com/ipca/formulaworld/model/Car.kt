package com.ipca.formulaworld.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "car_table")
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "object_id") val objectId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "classification") val classification: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "team") val team: String?= "",
    @ColumnInfo(name = "detail") val detail: String?= ""){
    @Ignore
    var image: Bitmap? = null
}
