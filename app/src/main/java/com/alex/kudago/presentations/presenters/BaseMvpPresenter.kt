package com.alex.kudago.presentations.presenters

import com.alex.kudago.presentations.views.BaseMvpView

/**
 * Created by alex on 07.05.2018.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attatchView(mvpView: V)
    fun detatchView()
}