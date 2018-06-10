package com.alex.kudago.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by alex on 16.05.2018.
 */
@Dao
interface EventDao {


    @Query("SELECT * FROM event")
    fun findAll(): List<CacheEvent>

    @Insert
    fun addEvents(event: List<CacheEvent>)

    @Query("DELETE FROM event")
    fun clearCache()
}