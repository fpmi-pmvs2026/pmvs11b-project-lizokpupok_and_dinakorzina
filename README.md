# Travel Planner

Travel Planner is a student Android application for planning trips, places to visit, and expected travel expenses. The current implementation focuses on Participant 1 responsibilities: Jetpack Compose UI, navigation, ViewModel state, user actions, validation, and unit tests. Data is temporarily stored in `FakeTripRepository`, which can later be replaced by Room and API implementations.

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

Participant 2 can continue by replacing `FakeTripRepository` with Room/API-backed implementations through the existing `TripRepository` interface, adding GitHub infrastructure, APK artifact workflow, Wiki, and GitHub Pages.

## Local Development

Run unit tests:

```bash
./gradlew test
```

Build a debug APK:

```bash
./gradlew assembleDebug
```
