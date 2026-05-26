# Presentation Outline

## Participant 2 — Дина Корзина

### Slide 1: Database (Room / SQLite)

- ER diagram: TRIPS ──o{ PLACES (cascade delete)
- `TripEntity` / `PlaceEntity` — `@Entity`, FK, index
- `TripWithPlaces` — `@Embedded` + `@Relation`
- `TripDao` — `@Dao` with Flow queries + suspend writes
- `AppDatabase` — singleton via `Room.databaseBuilder`

### Slide 2: REST Countries API

- Retrofit 2.9.0 + OkHttp 4.12.0 + Gson 2.10.1
- `CountriesApiService` — `@GET("name/{name}")`
- `CountryDto` → `DtoMappers.toDomain()` → `DestinationInfo`
- `DestinationInfoUiState`: `isLoading / data / errorMessage`
- ViewModel wraps in `Loading → Success / Error`

### Slide 3: Coroutines & Flow

- `suspend fun` for DB writes → `Dispatchers.IO`
- `Flow<List<Trip>>` from Room — reactive, no polling
- `DestinationInfoRepositoryImpl.getDestinationInfo` → `withContext(Dispatchers.IO)`
- UI: `StateFlow.collectAsState()` on `Dispatchers.Main`
- `AppContainer` holds a `CoroutineScope(SupervisorJob())`

### Slide 4: GitHub Organisation & Projects

- Organisation: `fpmi-pmvs2026`
- Repository: `pmvs11b-project-lizokpupok_and_dinakorzina`
- Branches: `main`, `feature/room-database`
- GitHub Projects — Kanban: To Do / In Progress / Done
- Issues for each task (DB, API, tests, docs, CI/CD)

### Slide 5: GitHub Actions CI/CD

- File: `.github/workflows/android-ci.yml`
- Triggers: push / PR → `main`, `develop`, `feature/**`
- Job `build`: clean → unit tests → assembleDebug → APK artifact
- Job `instrumented-tests` (main only): Android emulator API 29
- Artifact: `app-debug.apk` downloadable from Actions tab

### Slide 6: Testing

| Test file | Type | Tests |
|-----------|------|-------|
| `EntityMappersTest` | Unit | 6 — TripEntity↔Trip, PlaceEntity↔Place, unknown category |
| `DtoMappersTest` | Unit | 8 — CountryDto→DestinationInfo, null handling |
| `TripDaoTest` | Room in-memory | 5 — insert, cascade delete, Flow, toggle |

### Slide 7: Documentation

- `README.md` — setup, tech stack, contributing guide
- `docs/` — GitHub Pages: index, requirements, architecture, DB, CI/CD
- `wiki/` — 7 GitHub Wiki pages
- `schema.sql` — full DDL
