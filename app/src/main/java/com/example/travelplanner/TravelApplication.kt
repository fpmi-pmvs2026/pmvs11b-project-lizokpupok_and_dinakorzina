package com.example.travelplanner

import android.app.Application

class TravelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.initialize(this)
    }
}
