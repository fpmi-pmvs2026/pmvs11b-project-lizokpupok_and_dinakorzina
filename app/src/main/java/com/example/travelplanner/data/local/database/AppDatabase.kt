package com.example.travelplanner.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelplanner.data.local.dao.TripDao
import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity

@Database(
    entities = [TripEntity::class, PlaceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "travel_planner.db"
                ).build().also { INSTANCE = it }
            }
    }
}
