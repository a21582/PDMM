package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.Car

@Dao
interface CarDao {
    @Query("SELECT * FROM car_table")
    fun getAll(): List<Car>
    @Query("SELECT * FROM car_table ORDER BY classification")
    fun getAllOrderByCar(): List<Car>
    @Query("SELECT * FROM car_table WHERE id = :id")
    fun findById(id: Int): Car
    @Query("SELECT * FROM car_table WHERE object_id = :id")
    fun findByObjectId(id: String): Car
    @Insert
    fun insertAll(vararg car: Car)
    @Delete
    fun delete(car: Car)
    @Update
    fun updateCar(vararg car: Car)
}