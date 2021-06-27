package com.ipca.formulaworld.Dao

import androidx.room.*
import com.ipca.formulaworld.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): List<News>

    @Query("SELECT * FROM news_table ORDER BY created DESC")
    fun getAllNewsByCreated(): List<News>

    @Query("SELECT * FROM news_table WHERE id = :id")
    fun findById(id: Int): News

    @Query("SELECT * FROM news_table WHERE object_id = :id")
    fun findByObjectId(id: String): News

    @Insert
    fun insertAll(vararg news: News)

    @Delete
    fun delete(news: News)

    @Update
    fun updateNews(vararg news: News)
}