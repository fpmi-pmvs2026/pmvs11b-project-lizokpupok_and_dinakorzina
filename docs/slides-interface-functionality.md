# Slide Content: Participant 1

## 1. Application Interface

- Travel Planner uses Jetpack Compose and Material 3.
- The interface is organized around a trip list, trip details, forms, budget overview, and about screen.
- Reusable components keep the UI consistent: top bar, trip cards, place cards, empty state, budget summary, category chips, and primary action buttons.

## 2. Main Screens

- Trips screen: shows all trips, their destination, dates, budget, places count, and status.
- Add/Edit trip screen: lets the user enter title, destination, dates, budget, and note.
- Trip details screen: shows full trip information, places to visit, budget summary, and actions.
- Add place screen: saves a place with category and estimated cost.
- Budget screen: shows total budget, planned expenses, remaining budget, and category totals.
- About screen: explains the project, roles, stack, and purpose.

## 3. User Scenario

- The user opens the app and sees planned trips.
- The user creates a new trip and fills in required fields.
- The user opens trip details and adds places to visit.
- The user marks visited places while traveling.
- The user checks the Budget screen to control spending.
- The user edits or deletes trip data when plans change.

## 4. Navigation

- Navigation Compose routes:
- `trips`
- `trip_details/{tripId}`
- `add_trip`
- `edit_trip/{tripId}`
- `add_place/{tripId}`
- `budget/{tripId}`
- `about`

## 5. ViewModel Logic

- `TripsViewModel` observes the trip list.
- `EditTripViewModel` stores form state, validates input, and saves new or edited trips.
- `TripDetailsViewModel` loads a selected trip and handles delete/toggle place actions.
- `AddPlaceViewModel` validates and saves places.
- `BudgetViewModel` calculates expenses and category totals.

## 6. User Actions

- Create, edit, and delete trips.
- Add places to a trip.
- Toggle `isVisited` for a place.
- Delete places from the list.
- Select a place category.
- Navigate between list, details, forms, budget, and about screens.

## 7. Unit Testing

- Trip form validation tests check empty title, empty destination, negative budget, and valid data.
- Budget calculation tests check remaining budget and over-budget detection.
- Fake repository tests check adding trips, deleting trips, adding places, and toggling visited state.
