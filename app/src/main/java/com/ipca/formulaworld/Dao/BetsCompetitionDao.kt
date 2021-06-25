package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.BetsCompetition

@Dao
interface BetsCompetitionDao {
    @Query("SELECT DISTINCT competition FROM bets_table")
    fun getAll(): List<BetsCompetition>
    @Query("SELECT * FROM bets_table WHERE id = :id")
    fun findById(id: Int): BetsCompetition
    @Query("SELECT * FROM bets_table WHERE object_id = :id")
    fun findByObjectId(id: String): BetsCompetition
    @Query("SELECT * FROM bets_table WHERE competition = :id")
    fun findByCompetitionId(id: String): List<BetsCompetition>
    @Insert
    fun insertAll(vararg bets: BetsCompetition)
}