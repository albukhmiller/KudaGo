package com.alex.kudago.presentations.ui.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.alex.kudago.App
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.presentations.presenters.CitiesPresenter
import com.alex.kudago.presentations.ui.recyclerView.adapters.CitiesAdapter
import com.alex.kudago.presentations.views.CitiesView
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.select_city_toolbar.*
import org.jetbrains.anko.toast

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
        mvpPresenter.onLoadCitiesList()
    }

    override fun onSuccessLoadCitiesList(cities: ArrayList<City>) {
        rvCities.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvCities.hasFixedSize()
        rvCities.adapter = CitiesAdapter(cities, intent.getStringExtra("city")) {
            intent.putExtra("newCity", it)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onFailureLoadCitiesList() {
        toast("При загрузке списка городов произошла ошибка")
    }

}
