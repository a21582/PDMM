package com.ipca.formulaworld.model

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "pilot_table")
data class Pilot(
    //@PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: Drawable,
    @ColumnInfo(name = "classification") val classification: Int,
    @ColumnInfo(name = "year") val year: Int)
