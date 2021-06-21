package com.ipca.formulaworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipca.formulaworld.Dao.BetsDao
import com.ipca.formulaworld.Dao.PilotDao
import com.ipca.formulaworld.model.Bets
import com.ipca.formulaworld.model.Pilot

//import com.ipca.formulaworld.model.bets

@Database(entities = [Pilot::class, Bets::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun pilotDao(): PilotDao
    abstract fun betsDao(): BetsDao
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