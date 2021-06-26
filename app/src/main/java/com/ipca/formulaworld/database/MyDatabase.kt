package com.ipca.formulaworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipca.formulaworld.Dao.*
import com.ipca.formulaworld.model.*


@Database(entities = [Pilot::class, Team::class, BetsCompetition::class, BetsTeams::class, BetsPlayers::class, Events::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun pilotDao(): PilotDao
    abstract fun teamDao(): TeamDao
    abstract fun betsCompetitionDao(): BetsCompetitionDao
    abstract fun betsTeamsDao(): BetsTeamsDao
    abstract fun betsPlayersDao(): BetsPlayersDao
    abstract fun eventsDao(): EventsDao
    companion object {
        @Volatile private var instance: MyDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            MyDatabase::class.java, "formulaworld.db")
            .build()
    }
}