package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM team_table")
    fun getAll(): List<Team>
    @Query("SELECT * FROM team_table ORDER BY classification")
    fun getAllOrderByClassification(): List<Team>
    @Query("SELECT * FROM team_table WHERE id = :id")
    fun findById(id: Int): Team
    @Query("SELECT * FROM team_table WHERE object_id = :id")
    fun findByObjectId(id: String): Team
    @Insert
    fun insertAll(vararg team: Team)
    @Delete
    fun delete(team: Team)
    @Update
    fun updateTeam(vararg team: Team)
}