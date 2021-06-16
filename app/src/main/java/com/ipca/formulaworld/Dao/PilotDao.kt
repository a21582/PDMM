package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.Pilot

@Dao
interface PilotDao {
    @Query("SELECT * FROM pilot_table")
    fun getAll(): List<Pilot>
    @Query("SELECT * FROM pilot_table WHERE id = :id")
    fun findById(id: Int): Pilot
    @Query("SELECT * FROM pilot_table WHERE object_id = :id")
    fun findByObjectId(id: String): Pilot
    @Insert
    fun insertAll(vararg pilot: Pilot)
    @Delete
    fun delete(pilot: Pilot)
    @Update
    fun updatePilot(vararg pilot: Pilot)
}