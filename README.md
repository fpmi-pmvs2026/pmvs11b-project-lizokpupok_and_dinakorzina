# Travel Planner

[![Android CI](https://github.com/fpmi-pmvs2026/pmvs11b-project-lizokpupok_and_dinakorzina/actions/workflows/android-ci.yml/badge.svg)](https://github.com/fpmi-pmvs2026/pmvs11b-project-lizokpupok_and_dinakorzina/actions/workflows/android-ci.yml)

Travel Planner — мобильное Android-приложение для планирования поездок. Пользователь создаёт поездки, добавляет места для посещения, отслеживает бюджет и получает информацию о странах назначения через внешний REST API. Приложение построено на Clean Architecture: Jetpack Compose UI, Room для локального хранения данных, Retrofit для API-запросов, Coroutines и Flow для асинхронной работы.

## Installation

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/fpmi-pmvs2026/pmvs11b-project-lizokpupok_and_dinakorzina.git
   cd pmvs11b-project-lizokpupok_and_dinakorzina
   ```
2. Откройте проект в **Android Studio** (2025.3.1+).
3. Дождитесь завершения Gradle Sync.
4. Выберите эмулятор или устройство с **Android API 24+**.
5. Нажмите **Run ▶** или соберите APK:
   ```bash
   ./gradlew assembleDebug
   # APK: app/build/outputs/apk/debug/app-debug.apk
   ```

## Usage

1. Open the app to see the list of planned trips.
2. Tap the add button to create a new trip with title, destination, travel dates, budget, and note.
3. Open a trip card to review trip details, planned places, and a short budget summary.
4. Add places to visit with description, estimated cost, category, and note.
5. Mark places as visited when they are completed.
6. Open the Budget screen to compare the total trip budget with planned expenses and category totals.
7. Edit trip information from the details screen or delete trips and places when they are no longer needed.
8. Open the About screen to see the project description, roles, stack, and purpose.

## Contributing

Participant 1 implemented:

- Android project setup for the Travel Planner app.
- Jetpack Compose interface with Material 3.
- Main screens: trips list, trip form, trip details, add place, budget, and about.
- Navigation Compose routes for the multi-screen flow.
- ViewModel classes with `StateFlow` UI state.
- User action handling for saving, editing, deleting, toggling visited places, and selecting categories.
- Unit tests for form validation, budget calculation, and fake repository logic.
- Usage documentation for the user scenario.
- Slide content about interface and functionality.

Participant 2 (Диана) реализовала:

- Room / SQLite: `TripEntity`, `PlaceEntity`, `TripWithPlaces`, `TripDao`, `AppDatabase`.
- `RoomTripRepository` — заменяет `FakeTripRepository`, реализует тот же `TripRepository`.
- REST Countries API (Retrofit + OkHttp + Gson): информация о стране назначения.
- Coroutines и Flow: все DB/API операции на `Dispatchers.IO`.
- `DestinationInfoViewModel` — Loading / Success / Error состояния.
- Unit-тесты: `EntityMappersTest`, `DtoMappersTest`.
- Instrumented тесты: `TripDaoTest` на Room in-memory DB.
- GitHub Actions CI/CD: сборка APK + unit-тесты + instrumented тесты.
- GitHub Pages документация (`docs/`).
- Wiki страницы (`wiki/`).
- SQL-схема (`schema.sql`).

## Local Development

Run unit tests:

```bash
./gradlew testDebugUnitTest
```

Run instrumented tests (requires emulator or device):

```bash
./gradlew connectedDebugAndroidTest
```

Build a debug APK:

```bash
./gradlew assembleDebug
```

## Tech Stack

| Layer | Technology |
|-------|-----------|
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose |
| State | ViewModel + StateFlow |
| Database | Room 2.7.1 (SQLite) |
| Network | Retrofit 2.9.0 + OkHttp + Gson |
| Async | Coroutines + Flow |
| CI/CD | GitHub Actions |
| Tests | JUnit 4, Room in-memory, Espresso |
