package com.example.travelplanner.data.local.mapper

import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity
import com.example.travelplanner.data.local.relation.TripWithPlaces
import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.model.Trip

fun TripEntity.toDomain(places: List<Place> = emptyList()): Trip = Trip(
    id = id,
    title = title,
    destination = destination,
    startDate = startDate,
    endDate = endDate,
    budget = budget,
    note = note,
    places = places
)

fun PlaceEntity.toDomain(): Place = Place(
    id = id,
    tripId = tripId,
    title = title,
    description = description,
    estimatedCost = estimatedCost,
    category = runCatching { PlaceCategory.valueOf(category) }.getOrDefault(PlaceCategory.OTHER),
    isVisited = isVisited,
    note = note
)

fun Trip.toEntity(): TripEntity = TripEntity(
    id = id,
    title = title,
    destination = destination,
    startDate = startDate,
    endDate = endDate,
    budget = budget,
    note = note
)

fun Place.toEntity(): PlaceEntity = PlaceEntity(
    id = id,
    tripId = tripId,
    title = title,
    description = description,
    estimatedCost = estimatedCost,
    category = category.name,
    isVisited = isVisited,
    note = note
)

fun TripWithPlaces.toDomain(): Trip = trip.toDomain(places.map { it.toDomain() })
