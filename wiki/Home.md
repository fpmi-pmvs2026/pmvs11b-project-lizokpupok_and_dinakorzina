# Travel Planner — Wiki

Welcome to the Travel Planner project wiki.

## Navigation

| Page | Description |
|------|-------------|
| [Functional Requirements](Functional-Requirements) | FR-01 … FR-10, Use Case Diagram |
| [Use Cases](Use-Cases) | Actor, scenarios, pre/post-conditions |
| [File Structure](File-Structure) | Package layout, Clean Architecture layers |
| [Additional Spec](Additional-Spec) | Non-functional requirements, API spec |
| [Database Schema](Database-Schema) | ER diagram, tables, SQL |
| [Presentation](Presentation) | Slide outline for each participant |

## Team

| # | Participant | Responsibilities |
|---|-------------|-----------------|
| 1 | Alizaveta (lizokpupok) | UI (Jetpack Compose), navigation, ViewModels for trips/places |
| 2 | Дина (dinakorzina) | Room/SQLite, REST Countries API, CI/CD, tests, documentation |

## Tech Stack

- **Language:** Kotlin 2.0.21
- **UI:** Jetpack Compose + Material 3
- **Architecture:** Clean Architecture (domain / data / presentation / ui)
- **Database:** Room 2.7.1 (SQLite)
- **Network:** Retrofit 2.9.0 + REST Countries API v3.1
- **Async:** Coroutines 1.9.0 + Flow
- **CI/CD:** GitHub Actions → APK artifact
