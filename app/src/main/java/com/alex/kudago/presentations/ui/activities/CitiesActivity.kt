package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.presentations.presenters.CitiesPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.CitiesAdapter
import com.alex.kudago.presentations.views.CitiesView
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.select_city_toolbar.*
import org.jetbrains.anko.support.v4.onRefresh


class CitiesActivity : BaseActivity<CitiesView, CitiesPresenter>(), CitiesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        setSupportActionBar(select_city_toolbar)
        select_city_toolbar.setNavigationIcon(R.drawable.ic_back)
        select_city_toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED, intent)
            onBackPressed()
        }

        swipeCity.setColorSchemeResources(R.color.colorRed)
        swipeCity.onRefresh { mvpPresenter.onLoadCitiesList() }
        mvpPresenter.onLoadCitiesList()
    }

    override fun onSuccessLoadCitiesList(cities: ArrayList<City>) {
        swipeCity.isRefreshing = false
        rvCities.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvCities.hasFixedSize()
        rvCities.adapter = CitiesAdapter(cities, intent.getStringExtra("city")) {
            intent.putExtra("newCity", it)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        layoutLoaderCity.visibility = View.GONE
        select_city_toolbar.visibility = View.VISIBLE
        rootViewError.visibility = View.GONE
    }


    override fun onFailureLoadCitiesList() {
        swipeCity.isRefreshing = false
        layoutLoaderCity.visibility = View.GONE
        rootViewError.visibility = View.VISIBLE
        showSnackbar()
    }

}
