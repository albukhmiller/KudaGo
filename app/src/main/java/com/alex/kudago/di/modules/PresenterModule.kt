package com.alex.kudago.di.modules

import com.alex.kudago.presentations.presenters.CitiesPresenter
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.presentersImpls.CitiesPresenterImpl
import com.alex.kudago.presentations.presentersImpls.EventsPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by alex on 07.05.2018.
 */

@Module
@Singleton
class PresenterModule {

    @Singleton
    @Provides
    fun providesEventsPresenter(presenterImpl: EventsPresenterImpl): EventsPresenter = presenterImpl

    @Provides
    fun providesCitiesPresenter(presenterImpl: CitiesPresenterImpl): CitiesPresenter = presenterImpl
}