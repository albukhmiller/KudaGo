package com.alex.kudago.presentations.ui.activities

import android.os.Bundle
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView

class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
    }
}
