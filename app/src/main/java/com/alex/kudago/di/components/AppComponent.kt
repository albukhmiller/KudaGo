package com.alex.kudago.di.components

import android.app.Application
import com.alex.kudago.presentations.ui.activities.EventsActivity
import com.alex.kudago.di.modules.AppModule
import com.alex.kudago.di.modules.PresenterModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alex on 07.05.2018.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,
        PresenterModule::class))
interface AppComponent {

    fun inject(app: Application)
    fun inject(mainActivity: EventsActivity)
}