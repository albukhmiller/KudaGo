package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.presentations.presenters.BaseMvpPresenter
import com.alex.kudago.presentations.views.BaseMvpView

/**
 * Created by alex on 07.05.2018.
 */
abstract class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attatchView(mvpView: V) {
        mView = mvpView
    }

    override fun detatchView() {
        mView = null
    }
}