package com.alex.kudago.presentations.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alex.kudago.R
import com.alex.kudago.presentations.presenters.BaseMvpPresenter
import com.alex.kudago.presentations.views.BaseMvpView
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.layout_error.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.design.snackbar
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

    protected fun showSnackbar() {
        snackbar(rootViewError, "Невозможно загрузить данные, проверьте соединение с интернетом")
                .view.backgroundColor = R.color.colorError
    }
}