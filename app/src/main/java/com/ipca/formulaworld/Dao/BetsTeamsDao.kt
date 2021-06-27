package com.ipca.formulaworld.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ipca.formulaworld.model.BetsPlayers
import com.ipca.formulaworld.model.BetsTeams

@Dao
interface BetsTeamsDao {
    @Query("SELECT * FROM bets_teams_table WHERE object_id = :id")
    fun findByObjectId(id: String): BetsTeams
    @Query("SELECT * FROM bets_teams_table WHERE competition = :id")
    fun findByCompetitionId(id: String): List<BetsTeams>
    @Insert
    fun insertAll(vararg bets: BetsTeams)
    @Update
    fun updateBetsTeams(vararg bets: BetsTeams)
}