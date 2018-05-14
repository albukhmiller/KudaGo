package com.alex.kudago.presentations.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.presenters.DetailEventPresenter
import com.alex.kudago.presentations.ui.imageSlider.SliderImageAdapter
import com.alex.kudago.presentations.views.DetailEventView
import com.alex.kudago.utlis.LoaderImage
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.toast
import ss.com.bannerslider.Slider

class DetailEventActivity : BaseActivity<DetailEventView, DetailEventPresenter>(), DetailEventView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        val eventIntent = intent.getParcelableExtra<Event>("event")
        mvpPresenter.onLoadDetailEvent(eventIntent.id)
        setListener()
        initView(eventIntent)
    }

    private fun setListener() {
        btnBack.setOnClickListener({ finish() })
    }


    private fun initView(event: Event) {
        Slider.init(LoaderImage())
        sliderImage.setAdapter(SliderImageAdapter(event.images))

        tvTitleDetailEvent.text = event.title
        tvPrice.text = event.price
        tvDescriptionDetailEvent.text = event.description
        tvLocationDetailEvent.text = event.place?.address
        tvDateEvent.text = mvpPresenter.convertData(event.dates[0].startDate, event.dates[0].endDate)
    }

    override fun onSuccessLoadDetailEvent(event: Event) {
        tvSubscription.text = event.subscription
    }

    override fun onFailureLoadDetailEvent() {
        toast("При загрузке события произошла ошибка")
    }
}
