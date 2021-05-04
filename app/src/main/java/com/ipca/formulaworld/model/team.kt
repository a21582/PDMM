package com.ipca.formulaworld.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "team_table")
data class team(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: ByteArray,
    @ColumnInfo(name = "classification") val classification: Int,
    @ColumnInfo(name = "year") val year: Int)
