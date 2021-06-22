package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.Pilot
import com.ipca.formulaworld.model.Bets

@Dao
interface BetsDao {
    @Query("SELECT DISTINCT competition FROM bets_table")
    fun getAll(): List<Bets>
    @Query("SELECT * FROM bets_table WHERE id = :id")
    fun findById(id: Int): Bets
    @Query("SELECT * FROM bets_table WHERE object_id = :id")
    fun findByObjectId(id: String): Bets
    @Query("SELECT * FROM bets_table WHERE competition = :id")
    fun findByCompetitionId(id: String): List<Bets>
    @Insert
    fun insertAll(vararg bets: Bets)
}