package com.example.travelplanner.domain.usecase

import com.example.travelplanner.domain.model.Trip
import com.example.travelplanner.domain.model.TripStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetTripStatus {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    operator fun invoke(trip: Trip, now: Date = Date()): TripStatus {
        val start = trip.startDate.toDateOrNull()
        val end = trip.endDate.toDateOrNull()

        return when {
            start == null || end == null -> TripStatus.PLANNED
            now.before(start) -> TripStatus.PLANNED
            now.after(end) -> TripStatus.COMPLETED
            else -> TripStatus.ACTIVE
        }
    }

    private fun String.toDateOrNull(): Date? = runCatching {
        dateFormat.parse(this)
    }.getOrNull()
}
