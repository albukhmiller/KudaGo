package com.alex.kudago.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by alex on 16.05.2018.
 */
@Database(entities = [(CacheEvent::class)], version = 4, exportSchema = false)
abstract class AppLocalDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}