package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.model.City
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.EventsAdapter
import com.alex.kudago.presentations.views.EventsView
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.events_toolbar.*
import kotlinx.android.synthetic.main.layout_error.*
import org.jetbrains.anko.intentFor
import java.lang.ref.WeakReference


class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView, SwipeRefreshLayout.OnRefreshListener {

    private val SELECT_CITY = 1
    private var city: City? = null
    private var itemsEvents = mutableListOf<CacheEvent>()
    private var adapter: EventsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_events)
        setSupportActionBar(events_toolbar)

        initRecyclerEvent()

        setListener()

        var fixedCity = mvpPresenter.getUserCity()
        if (fixedCity!![0].isEmpty()) {
            mvpPresenter.onSaveUserCity("Москва", "msk")
            fixedCity = mvpPresenter.getUserCity()
        }
        city = City(fixedCity!!.elementAt(1), fixedCity!!.elementAt(0), false, 0)

        btnSelectCity.text = city!!.name

        swipe.setColorSchemeResources(R.color.colorRed)
        swipe.setOnRefreshListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == SELECT_CITY)
                if (data != null) {
                    layoutLoader.visibility = View.VISIBLE

                    var newCity = data.getParcelableExtra<City>("newCity")
                    btnSelectCity.text = newCity.name
                    city!!.slug = newCity.slug

                    mvpPresenter.onSaveUserCity(newCity.name, city!!.slug)
                    mvpPresenter.onChangeCity(city!!.slug)
                    scrollEvents.scrollTo(0, 0)
                    itemsEvents.removeAll(itemsEvents)

                }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRefresh() {
        layoutLoader.visibility = View.VISIBLE
        mvpPresenter.onChangeCity(city!!.slug)
        itemsEvents.removeAll(itemsEvents)
    }

    override fun onSuccessLoadPreviewEvents(events: List<CacheEvent>) {
        app_bar.visibility = View.VISIBLE
        rootViewError.visibility = View.GONE
        itemsEvents.addAll(events)
        swipe.isRefreshing = false

        if (adapter == null) {
            rvEvents.adapter = setAdapterRvEvent(itemsEvents)
            layoutLoader.visibility = View.GONE
            events_toolbar.visibility = View.VISIBLE
        }

        layoutLoader.visibility = View.GONE
        adapter?.notifyDataSetChanged()
    }

    override fun onFailureLoadPreviewEvents() {
        swipe.isRefreshing = false
        app_bar.visibility = View.GONE
        rootViewError.visibility = View.VISIBLE
        showSnackbar()
    }

    private fun setListener() {
        btnSelectCity.setOnClickListener({
            startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY)
        })
        scrollEvents.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->

            if (v.getChildAt(v.childCount - 1) != null)
                if (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) && scrollY > oldScrollY) {
                    mvpPresenter.onLoadEvents(city!!.slug!!)
                }

        })
    }

    private fun initRecyclerEvent() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        rvEvents.setHasFixedSize(true)

        loadMovies()
    }

    private fun setAdapterRvEvent(event: List<CacheEvent>) = EventsAdapter(event) {
        startActivity(intentFor<DetailEventActivity>("event" to it))
    }

    private fun loadMovies() {

        var weakRefActivity = WeakReference(this)

        object : AsyncTask<Void, Void, List<CacheEvent>>() {
            override fun doInBackground(vararg params: Void?) = mvpPresenter.onLoadDataOfCache()

            override fun onPostExecute(result: List<CacheEvent>?) {
                super.onPostExecute(result)
                if (result!!.isNotEmpty()) {
                    layoutLoader.visibility = View.GONE
                    app_bar.visibility = View.VISIBLE
                    itemsEvents.addAll(result!!)
                    weakRefActivity.get()?.rvEvents?.adapter = setAdapterRvEvent(itemsEvents)
                    weakRefActivity.get()?.events_toolbar?.visibility = View.VISIBLE
                } else mvpPresenter.onLoadEvents(city!!.slug!!)
            }
        }.execute()
    }
}
