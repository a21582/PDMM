package com.ipca.formulaworld.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class car(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: ByteArray,
    @ColumnInfo(name = "equipa") val equipa: String,
    @ColumnInfo(name = "detail") val detail: String,
    @ColumnInfo(name = "year") val year: Int)
