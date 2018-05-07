package com.alex.kudago.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by alex on 07.05.2018.
 */

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = app

    @Provides
    @Singleton
    fun providesApp() = app
}