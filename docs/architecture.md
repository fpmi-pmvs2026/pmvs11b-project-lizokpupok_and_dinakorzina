# Architecture

Travel Planner uses **Clean Architecture** with three layers.

## Package structure

```
com.example.travelplanner/
├── domain/                     ← Business logic (no Android deps)
│   ├── model/Trip, Place, PlaceCategory, DestinationInfo, BudgetInfo, TripStatus
│   ├── repository/TripRepository, DestinationInfoRepository
│   └── usecase/TripFormValidator, PlaceFormValidator, CalculateBudgetInfo, GetTripStatus
│
├── data/                       ← Repository implementations (Participant 2)
│   ├── local/
│   │   ├── entity/TripEntity, PlaceEntity
│   │   ├── relation/TripWithPlaces
│   │   ├── dao/TripDao
│   │   ├── database/AppDatabase
│   │   ├── mapper/EntityMappers
│   │   └── repository/RoomTripRepository
│   ├── remote/
│   │   ├── api/CountriesApiService
│   │   ├── dto/CountryDto
│   │   ├── datasource/RemoteDataSource
│   │   ├── mapper/DtoMappers
│   │   └── repository/DestinationInfoRepositoryImpl
│   └── fake/FakeTripRepository  ← kept for reference/tests
│
├── presentation/               ← ViewModels
│   ├── trips/, tripdetails/, edittrip/, addplace/, budget/
│   └── destination/DestinationInfoViewModel (Participant 2)
│
├── ui/                         ← Jetpack Compose screens (Participant 1)
├── navigation/AppNavGraph, Routes
├── AppContainer                ← Manual DI (initialized in TravelApplication)
├── TravelApplication           ← Application class (Participant 2)
└── MainActivity
```

## Dependency flow

```
UI → ViewModel → TripRepository (interface)
                       ↓
               RoomTripRepository → TripDao → Room/SQLite
               
ViewModel → DestinationInfoRepository (interface)
                       ↓
               DestinationInfoRepositoryImpl → RemoteDataSource → REST Countries API
```

## Concurrency

| Operation | Dispatcher |
|-----------|-----------|
| DB reads (Flow) | Room handles internally |
| DB writes | `Dispatchers.IO` via `CoroutineScope` in `AppContainer` |
| API requests | `Dispatchers.IO` in `DestinationInfoRepositoryImpl` |
| UI updates | `Dispatchers.Main` via `StateFlow.collectAsState()` |
