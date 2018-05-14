package com.alex.kudago.presentations.presenters

import com.alex.kudago.presentations.views.DetailEventView

/**
 * Created by alex on 14.05.2018.
 */
interface DetailEventPresenter: BaseMvpPresenter<DetailEventView> {

    fun convertData(start: String, end: String) : String

    fun onLoadDetailEvent(id : Long)
}