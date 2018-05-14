package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.EventsAdapter
import com.alex.kudago.presentations.views.EventsView
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.events_toolbar.*
import kotlinx.android.synthetic.main.events_toolbar.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView {

    private val SELECT_CITY = 1
    private val BASE_URL = "public-api/v1.4/events/?expand=place,images&fields=description,is_free,price,dates,place,title,images&text_format=text"

    private var url: String? = null
    private var slug: String? = "msk"
    private var itemsEvents = ArrayList<Event>()
    private var adapter: EventsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        setSupportActionBar(events_toolbar)

        setListener()
        mvpPresenter.onLoadEvents(BASE_URL, slug!!)

        rvEvents.layoutManager = LinearLayoutManager(this)
        rvEvents.setHasFixedSize(true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == SELECT_CITY)
                if (data != null) {
                    var newCity = data.getParcelableExtra<City>("newCity")
                    events_toolbar.btnSelectCity.text = newCity.name
                    slug = newCity.slug
                    itemsEvents.removeAll(itemsEvents)
                    mvpPresenter.onLoadEvents(BASE_URL, slug!!)

                }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccessLoadPreviewEvents(events: PreviewEvents) {
        url = events.nextPage
        pbLoader.visibility = View.GONE
        itemsEvents.addAll(events.events)
        if (adapter == null) {
            adapter = EventsAdapter(itemsEvents)
            rvEvents.adapter = adapter
        } else
            rvEvents.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onFailureLoadPreviewEvents() {
        toast("При загрузке произошла ошибка")
    }

    private fun setListener() {
        events_toolbar.btnSelectCity.setOnClickListener({ startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY) })
        scrollEvents.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY > 70) {
                events_toolbar.elevation = 10F
            } else {
                events_toolbar.elevation = 0F
            }

            if (v.getChildAt(v.childCount - 1) != null)
                if (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) && scrollY > oldScrollY) {
                    pbLoader.visibility = View.VISIBLE
                    mvpPresenter.onLoadEvents(url!!, slug!!)
                }

        })

    }

}
