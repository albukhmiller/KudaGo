package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView
import kotlinx.android.synthetic.main.activity_events.*
import org.jetbrains.anko.intentFor

class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView {

    private val SELECT_CITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        setListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK)
            if (requestCode == SELECT_CITY)
                if (data != null)
                btnSelectCity.text = data.getStringExtra("newCity")
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setListener() {
        btnSelectCity.setOnClickListener({ startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY) })
    }
}
