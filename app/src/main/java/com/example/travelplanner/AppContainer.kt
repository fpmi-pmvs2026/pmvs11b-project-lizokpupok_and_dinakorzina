package com.example.travelplanner

import android.content.Context
import com.example.travelplanner.data.local.database.AppDatabase
import com.example.travelplanner.data.local.repository.RoomTripRepository
import com.example.travelplanner.data.remote.datasource.RemoteDataSource
import com.example.travelplanner.data.remote.repository.DestinationInfoRepositoryImpl
import com.example.travelplanner.domain.repository.DestinationInfoRepository
import com.example.travelplanner.domain.repository.TripRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object AppContainer {

    lateinit var tripRepository: TripRepository
        private set

    lateinit var destinationInfoRepository: DestinationInfoRepository
        private set

    fun initialize(context: Context) {
        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        val database = AppDatabase.getInstance(context)
        tripRepository = RoomTripRepository(database.tripDao(), applicationScope)
        destinationInfoRepository = DestinationInfoRepositoryImpl(RemoteDataSource())
    }
}
