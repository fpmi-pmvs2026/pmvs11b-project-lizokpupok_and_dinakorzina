# File Structure

```
app/src/main/java/com/example/travelplanner/
│
├── domain/                          ← Pure Kotlin, no Android deps
│   ├── model/
│   │   ├── Trip.kt
│   │   ├── Place.kt
│   │   ├── PlaceCategory.kt
│   │   ├── DestinationInfo.kt
│   │   ├── BudgetInfo.kt
│   │   └── TripStatus.kt
│   ├── repository/
│   │   ├── TripRepository.kt        ← interface
│   │   └── DestinationInfoRepository.kt ← interface
│   └── usecase/
│       ├── TripFormValidator.kt
│       ├── PlaceFormValidator.kt
│       ├── CalculateBudgetInfo.kt
│       └── GetTripStatus.kt
│
├── data/                            ← Participant 2
│   ├── local/
│   │   ├── entity/
│   │   │   ├── TripEntity.kt        ← @Entity, Room table
│   │   │   └── PlaceEntity.kt       ← @Entity, FK → trips(id) CASCADE
│   │   ├── relation/
│   │   │   └── TripWithPlaces.kt    ← @Embedded + @Relation
│   │   ├── dao/
│   │   │   └── TripDao.kt           ← @Dao, Flow queries
│   │   ├── database/
│   │   │   └── AppDatabase.kt       ← @Database singleton
│   │   ├── mapper/
│   │   │   └── EntityMappers.kt     ← Entity ↔ Domain
│   │   └── repository/
│   │       └── RoomTripRepository.kt
│   ├── remote/
│   │   ├── api/
│   │   │   └── CountriesApiService.kt ← Retrofit @GET
│   │   ├── dto/
│   │   │   └── CountryDto.kt
│   │   ├── datasource/
│   │   │   └── RemoteDataSource.kt  ← Retrofit singleton
│   │   ├── mapper/
│   │   │   └── DtoMappers.kt        ← DTO → Domain
│   │   └── repository/
│   │       └── DestinationInfoRepositoryImpl.kt
│   └── fake/
│       └── FakeTripRepository.kt    ← for tests / reference
│
├── presentation/                    ← ViewModels
│   ├── trips/
│   ├── tripdetails/
│   ├── edittrip/
│   ├── addplace/
│   ├── budget/
│   └── destination/                 ← Participant 2
│       ├── DestinationInfoUiState.kt
│       └── DestinationInfoViewModel.kt
│
├── ui/                              ← Jetpack Compose screens (Participant 1)
├── navigation/
│   ├── AppNavGraph.kt
│   └── Routes.kt
├── AppContainer.kt                  ← Manual DI
├── TravelApplication.kt             ← Application class (Participant 2)
└── MainActivity.kt
```

## Key Dependency Rule

> Each layer only depends on layers **above** it in the diagram:
> `ui → presentation → domain ← data`

`data` implements `domain` interfaces; `domain` never imports `data` or `ui`.
