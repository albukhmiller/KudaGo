package com.alex.kudago

import android.app.Application
import com.alex.kudago.di.components.AppComponent
import com.alex.kudago.di.components.DaggerAppComponent
import com.alex.kudago.di.modules.AppModule

/**
 * Created by alex on 07.05.2018.
 */
class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this)
    }
}