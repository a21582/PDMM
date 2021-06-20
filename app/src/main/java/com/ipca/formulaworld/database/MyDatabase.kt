package com.ipca.formulaworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipca.formulaworld.Dao.PilotDao
import com.ipca.formulaworld.Dao.TeamDao
import com.ipca.formulaworld.model.Pilot
import com.ipca.formulaworld.model.Team

@Database(entities = [Pilot::class, Team::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun pilotDao(): PilotDao
    abstract fun teamDao(): TeamDao
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