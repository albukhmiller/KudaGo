package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.EventsAdapter
import com.alex.kudago.presentations.views.EventsView
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_events.view.*
import kotlinx.android.synthetic.main.events_toolbar.*
import kotlinx.android.synthetic.main.events_toolbar.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.margin
import org.jetbrains.anko.toast

class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView {

    private val SELECT_CITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        setSupportActionBar(events_toolbar)
        setListener()
        mvpPresenter.onLoadEvents("msk")

        rvEvents.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvEvents.setHasFixedSize(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == SELECT_CITY)
                if (data != null) {
                    var newCity = data.getParcelableExtra<City>("newCity")
                    events_toolbar.btnSelectCity.text = newCity.name
                    mvpPresenter.onLoadEvents(newCity.slug)
                }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccessLoadPreviewEvents(events: PreviewEvents) {
        rvEvents.adapter = EventsAdapter(events.events)
    }

    override fun onFailureLoadPreviewEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setListener() {
        events_toolbar.btnSelectCity.setOnClickListener({ startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY) })
        scrollEvents.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->


            if (scrollY  > 70) {
                events_toolbar.elevation = 10F
            }
            else {
                events_toolbar.elevation = 0F
            }
        })
    }
}
