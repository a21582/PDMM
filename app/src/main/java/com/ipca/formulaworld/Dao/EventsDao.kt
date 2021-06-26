package com.ipca.formulaworld.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ipca.formulaworld.model.Events

@Dao
interface EventsDao {
    @Query("SELECT * FROM events_table")
    fun getAll(): List<Events>
    @Query("SELECT * FROM events_table WHERE object_id = :id")
    fun findByObjectId(id: String): Events
    @Query("SELECT * FROM events_table WHERE eventDay = :id")
    fun findByDate(id: String): Events
    @Insert
    fun insertAll(vararg events: Events)
}