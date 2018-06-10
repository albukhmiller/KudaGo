package com.alex.kudago.di.components

import android.app.Application
import com.alex.kudago.presentations.ui.activities.EventsActivity
import com.alex.kudago.di.modules.AppModule
import com.alex.kudago.di.modules.DataModule
import com.alex.kudago.di.modules.PresenterModule
import com.alex.kudago.presentations.ui.activities.CitiesActivity
import com.alex.kudago.presentations.ui.activities.DetailEventActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alex on 07.05.2018.
 */

@Singleton
@Component(modules = [(AppModule::class),
    (PresenterModule::class),
    (DataModule::class)])
interface AppComponent {

    fun inject(app: Application)
    fun inject(mainActivity: EventsActivity)
    fun inject(citiesActivity: CitiesActivity)
    fun inject(detailEventActivity: DetailEventActivity)
}