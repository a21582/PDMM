package com.ipca.formulaworld.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "calendar_table")
data class calendar(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "inform") val inform: String,
    @ColumnInfo(name = "created") val created: Date,
    @ColumnInfo(name = "eventDay") val eventDate: Date)