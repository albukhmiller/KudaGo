package com.alex.kudago.presentations.ui.activities

import android.os.Bundle
import android.view.View
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.presenters.DetailEventPresenter
import com.alex.kudago.presentations.ui.imageSlider.SliderImageAdapter
import com.alex.kudago.presentations.views.DetailEventView
import com.alex.kudago.utlis.LoaderImage
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_event.*
import ss.com.bannerslider.Slider
import android.content.Intent
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.widget.RelativeLayout
import com.alex.kudago.data.database.CacheEvent
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.detail_event_toolbar.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.select_city_toolbar.*
import org.jetbrains.anko.support.v4.onRefresh


class DetailEventActivity : BaseActivity<DetailEventView, DetailEventPresenter>(), DetailEventView {


    private var eventIntent: CacheEvent? = null

    private val ZOOM_LEVEL = 14f
    private val MAP_PACKAGE = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        eventIntent = intent.getParcelableExtra("event")
        mvpPresenter.onLoadDetailEvent(eventIntent!!.id)

        swipeLoadDetail.setColorSchemeResources(R.color.colorRed)
        swipeLoadDetail.onRefresh { mvpPresenter.onLoadDetailEvent(eventIntent!!.id) }
        setListener()

        initView()

    }

    override fun onSuccessLoadDetailEvent(event: Event) {
        rootViewError.visibility = View.GONE
        swipeLoadDetail.isRefreshing = false
        layoutLoaderDetail.visibility = View.GONE
        Slider.init(LoaderImage())
        sliderImage.setAdapter(SliderImageAdapter(event!!.images))
        tvSubscription.text = event.subscription
    }

    override fun onFailureLoadDetailEvent() {
        swipeLoadDetail.isRefreshing = false
        layoutLoaderDetail.visibility = View.GONE
        rootViewError.visibility = View.VISIBLE
        showSnackbar()
    }


    private fun initMap(event: CacheEvent) {

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(OnMapReadyCallback { googleMap ->
            googleMap ?: return@OnMapReadyCallback
            var coordinates = LatLng(event.lat!!, event.lnt!!)

            with(googleMap) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, ZOOM_LEVEL))
                addMarker(MarkerOptions().position(coordinates))
            }
        })


    }

    private fun setListener() {
        btnBack.setOnClickListener({ finish() })

        btnDirections.setOnClickListener({
            val gmmIntentUri = Uri.parse("google.navigation:q=${eventIntent!!.lat},${eventIntent!!.lnt}&avoid=tf")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.`package` = MAP_PACKAGE
            startActivity(mapIntent)
        })
    }

    private fun initView() {


        tvTitleDetailEvent.text = eventIntent!!.title

        if (!eventIntent!!.isFree)
            tvPrice.text = eventIntent!!.price
        else
            tvPrice.visibility = View.GONE
        tvDescriptionDetailEvent.text = eventIntent!!.description

        if (eventIntent!!.address != null) {
            initMap(eventIntent!!)
            tvLocationDetailEvent.text = eventIntent!!.address
        } else {
            map.view?.visibility = View.GONE
            btnDirections.visibility = View.GONE
            tvLocationDetailEvent.visibility = View.GONE
        }
        tvDateEvent.text = eventIntent?.date
    }


}
