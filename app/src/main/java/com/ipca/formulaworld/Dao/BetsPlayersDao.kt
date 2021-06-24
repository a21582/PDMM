package com.ipca.formulaworld.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ipca.formulaworld.model.BetsPlayers

@Dao
interface BetsPlayersDao {
    @Query("SELECT * FROM bets_players_table WHERE object_id = :id")
    fun findByObjectId(id: String): BetsPlayers
    @Query("SELECT * FROM bets_players_table WHERE competition = :id")
    fun findByCompetitionId(id: String): List<BetsPlayers>
    @Insert
    fun insertAll(vararg bets: BetsPlayers)
}