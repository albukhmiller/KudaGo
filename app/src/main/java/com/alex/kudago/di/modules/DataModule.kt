package com.alex.kudago.di.modules

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
    fun providesServerApi() = Retrofit.Builder()
            .baseUrl("https://kudago.com/public-api/v1.4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerKudaGoApi::class.java)
}