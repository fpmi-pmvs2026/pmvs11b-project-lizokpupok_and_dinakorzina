package com.example.travelplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity
import com.example.travelplanner.data.local.relation.TripWithPlaces
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Query("SELECT * FROM trips ORDER BY id DESC")
    fun observeTrips(): Flow<List<TripEntity>>

    @Transaction
    @Query("SELECT * FROM trips WHERE id = :id")
    fun observeTripWithPlaces(id: Long): Flow<TripWithPlaces?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity): Long

    @Update
    suspend fun updateTrip(trip: TripEntity)

    @Query("DELETE FROM trips WHERE id = :id")
    suspend fun deleteTripById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceEntity): Long

    @Update
    suspend fun updatePlace(place: PlaceEntity)

    @Query("DELETE FROM places WHERE id = :placeId")
    suspend fun deletePlaceById(placeId: Long)

    @Query("SELECT * FROM places WHERE tripId = :tripId")
    fun observePlacesForTrip(tripId: Long): Flow<List<PlaceEntity>>

    @Query("SELECT isVisited FROM places WHERE id = :placeId")
    suspend fun getPlaceVisited(placeId: Long): Boolean?

    @Query("UPDATE places SET isVisited = :isVisited WHERE id = :placeId")
    suspend fun updatePlaceVisited(placeId: Long, isVisited: Boolean)
}
