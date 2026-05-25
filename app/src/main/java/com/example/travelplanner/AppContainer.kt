package com.example.travelplanner

import com.example.travelplanner.data.fake.FakeTripRepository
import com.example.travelplanner.domain.repository.TripRepository

object AppContainer {
    val tripRepository: TripRepository = FakeTripRepository()
}
