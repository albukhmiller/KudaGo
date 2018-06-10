package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.domain.interactors.InteractorListener
import com.alex.kudago.domain.interactors.LoaderDetailEventInteractor
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.presenters.DetailEventPresenter
import com.alex.kudago.presentations.views.DetailEventView
import javax.inject.Inject

/**
 * Created by alex on 14.05.2018.
 */
class DetailEventPresenterImpl @Inject constructor(private val loaderDetailEventInteractor: LoaderDetailEventInteractor) : BaseMvpPresenterImpl<DetailEventView>(), InteractorListener, DetailEventPresenter {

    init {
        loaderDetailEventInteractor.setLoaderDetailEventInteractorListener(this)
    }

    override fun onLoadDetailEvent(id: Long) {
        loaderDetailEventInteractor.getDetailEvent(id)
    }

    override fun <T> onSuccessLoad(event: T) {
        mView?.onSuccessLoadDetailEvent(event as Event)
    }

    override fun onFailureLoad() {
        mView?.onFailureLoadDetailEvent()
    }

}