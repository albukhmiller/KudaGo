package com.alex.kudago.data.database

import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.utlis.TimeConverter

/**
 * Created by alex on 29.05.2018.
 */
class MapperEvents {

    companion object {
        fun convertEventToCashEvent(previewEvents: PreviewEvents): MutableList<CacheEvent> {
            var timeConverter = TimeConverter()
            var events = previewEvents.events
            var cacheEvent = ArrayList<CacheEvent>()

            for (event in events) {
                var evCache = CacheEvent(id = event.id,
                        date = timeConverter.convertSrtingToDate(event.dates[0].startDate, event.dates[0].endDate),
                        title = event.title,
                        address = event.place?.address,
                        lat = event.place?.coords?.lat,
                        lnt = event.place?.coords?.lon,
                        description = event.description,
                        isFree = event.isFree,
                        image = event.images[0].urlImage,
                        price = event.price,
                        next = previewEvents.nextPage)
                cacheEvent.add(evCache)
            }
            return cacheEvent
        }
    }
}