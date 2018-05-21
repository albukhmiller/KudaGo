package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.domain.LoadDetailEventInteractorListener
import com.alex.kudago.domain.LoaderDetailEventInteractor
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.presenters.DetailEventPresenter
import com.alex.kudago.presentations.views.DetailEventView
import com.alex.kudago.utlis.TimeConverter
import javax.inject.Inject

/**
 * Created by alex on 14.05.2018.
 */
class DetailEventPresenterImpl @Inject constructor(private val loaderDetailEventInteractor: LoaderDetailEventInteractor) : BaseMvpPresenterImpl<DetailEventView>(), LoadDetailEventInteractorListener, DetailEventPresenter {

    init {
        loaderDetailEventInteractor.setLoaderDetailEventInteractorListener(this)
    }

    override fun onLoadDetailEvent(id: Long) {
            loaderDetailEventInteractor.getDetailEvent(id)
    }

    override fun onSuccessLoadDetailEvent(event: Event) {
            mView?.onSuccessLoadDetailEvent(event)
    }

    override fun onFailureLoadDetailEvent() {
        mView?.onFailureLoadDetailEvent()
    }
}