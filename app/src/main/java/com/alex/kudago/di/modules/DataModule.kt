package com.alex.kudago.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.alex.kudago.data.database.AppLocalDatabase
import com.alex.kudago.model.api.ServerKudaGoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by alex on 08.05.2018.
 */

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesServerApi(): ServerKudaGoApi {

        return Retrofit.Builder()
                .baseUrl("https://kudago.com/public-api/v1.4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServerKudaGoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context) = Room.databaseBuilder(context, AppLocalDatabase::class.java, "dbEvent")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun providesEventDao(db: AppLocalDatabase) = db.eventDao()
}