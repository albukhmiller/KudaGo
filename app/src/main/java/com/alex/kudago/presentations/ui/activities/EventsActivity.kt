package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import org.jetbrains.anko.intentFor


class EventsActivity : BaseActivity<EventsView, EventsPresenter>(), EventsView, SwipeRefreshLayout.OnRefreshListener {

    private val SELECT_CITY = 1

    private var url: String? = null
    private var slug: String = "msk"
    private var city: String = "Москва"
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
        if (fixedCity!!.isEmpty()) {
            mvpPresenter.onSaveUserCity("Москва", slug!!)
            fixedCity = mvpPresenter.getUserCity()
        }

        slug = fixedCity!!.elementAt(1)
        city = fixedCity!!.elementAt(0)

        btnSelectCity.text = city

        swipe.setColorSchemeResources(R.color.colorRed)
        swipe.setOnRefreshListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == SELECT_CITY)
                if (data != null) {
                    splashLayout.visibility = View.VISIBLE

                    var newCity = data.getParcelableExtra<City>("newCity")
                    btnSelectCity.text = newCity.name
                    slug = newCity.slug

                    mvpPresenter.onSaveUserCity(newCity.name, slug)
                    mvpPresenter.onChangeCity(slug)

                    rvEvents.scrollToPosition(0)
                    itemsEvents.removeAll(itemsEvents)

                }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRefresh() {
        mvpPresenter.onChangeCity(slug)
        itemsEvents.removeAll(itemsEvents)
    }

    override fun onSuccessLoadPreviewEvents(events: List<CacheEvent>) {
        layoutErrorInternet.visibility = View.GONE
        url = events[0].next
        itemsEvents.addAll(events)
        swipe.isRefreshing = false

        if (adapter == null) {
            rvEvents.adapter = setAdapterRvEvent(itemsEvents)
            splashLayout.visibility = View.GONE
            events_toolbar.visibility = View.VISIBLE
        }

        splashLayout.visibility = View.GONE
        adapter?.notifyDataSetChanged()
    }

    override fun onFailureLoadPreviewEvents() {
        swipe.isRefreshing = false
        layoutErrorInternet.visibility = View.VISIBLE
    }

    private fun setListener() {
        btnSelectCity.setOnClickListener({
            startActivityForResult(intentFor<CitiesActivity>("city" to btnSelectCity.text), SELECT_CITY)
        })
        scrollEvents.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->


            if (v.getChildAt(v.childCount - 1) != null)
                if (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) && scrollY > oldScrollY) {
                    mvpPresenter.onLoadEvents(url!!, slug!!)
                }

        })
    }

    private fun initRecyclerEvent() {
        rvEvents.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvEvents.setHasFixedSize(true)

        loadMovies()
    }

    private fun setAdapterRvEvent(event: List<CacheEvent>) = EventsAdapter(event) {
        startActivity(intentFor<DetailEventActivity>("event" to it))
    }

    private fun loadMovies() {
        object : AsyncTask<Void, Void, List<CacheEvent>>() {
            override fun doInBackground(vararg params: Void?) = mvpPresenter.onLoadDataOfCache()

            override fun onPostExecute(result: List<CacheEvent>?) {
                super.onPostExecute(result)
                if (result!!.isNotEmpty()) {
                    itemsEvents.addAll(result!!)
                    url = itemsEvents[itemsEvents.lastIndex].next
                    rvEvents.adapter = setAdapterRvEvent(itemsEvents)
                    events_toolbar.visibility = View.VISIBLE
                    splashLayout.visibility = View.GONE
                } else mvpPresenter.onLoadEvents(null, slug!!)
            }
        }.execute()
    }

}
