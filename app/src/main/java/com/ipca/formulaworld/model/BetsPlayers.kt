package com.ipca.formulaworld.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bets_players_table")
data class BetsPlayers(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "object_id") val objectId: String? = "",
    @ColumnInfo(name = "competition") val competition: String? = "",
    @ColumnInfo(name = "player") val player: String? = "",
    @ColumnInfo(name = "odd") val odd: String? = ""
): Serializable