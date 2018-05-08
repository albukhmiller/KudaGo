package com.alex.kudago.presentations.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alex.kudago.presentations.presenters.BaseMvpPresenter
import com.alex.kudago.presentations.views.BaseMvpView
import javax.inject.Inject

/**
 * Created by alex on 07.05.2018.
 */
abstract class BaseActivity<in V : BaseMvpView, P : BaseMvpPresenter<V>> : AppCompatActivity() {

    @Inject
    lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpPresenter.attatchView(this as V)
    }

    override fun onDestroy() {
        mvpPresenter.detatchView()
        super.onDestroy()
    }
}