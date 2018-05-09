package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.EventsAdapter
import com.alex.kudago.presentations.views.EventsView
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.activity_events.*
import org.jetbrains.anko.intentFor

class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView {

    private val SELECT_CITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
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
                    btnSelectCity.text = newCity.name
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
        btnSelectCity.setOnClickListener({ startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY) })
    }
}
