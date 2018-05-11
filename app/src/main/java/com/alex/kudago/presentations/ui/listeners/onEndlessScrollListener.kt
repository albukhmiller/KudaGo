package com.alex.kudago.presentations.ui.listeners

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by alex on 11.05.2018.
 */
abstract class onEndlessScrollListener(private var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {


    private var totalPreviousItem = 0
    private var countItemTop = 20 //количество элементов, находящихся выше, для начала загрузки
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var visibleCountItem = recyclerView?.childCount
        var totalItemCount = layoutManager.itemCount
        var firstVisibleCount = layoutManager.findFirstVisibleItemPosition()

        if (isLoading) {
            if (totalItemCount > totalPreviousItem) {
                isLoading = false
                totalPreviousItem = totalItemCount
            }
        }
        if (!isLoading && (totalItemCount - countItemTop) <= (firstVisibleCount + visibleCountItem!!)) {
            onLoadMore()
            isLoading = true
        }
    }

    abstract fun onLoadMore()

}