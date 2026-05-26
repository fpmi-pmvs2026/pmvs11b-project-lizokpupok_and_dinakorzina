# Database Schema

Room database: `travel_planner.db` (version 1, `exportSchema = false`)

## ER Diagram

```
TRIPS                          PLACES
─────────────────────          ──────────────────────────
id          INTEGER PK    ←──  id           INTEGER PK
title       TEXT               tripId       INTEGER FK
destination TEXT               title        TEXT
startDate   TEXT               description  TEXT
endDate     TEXT               estimatedCost REAL
budget      REAL               category     TEXT
note        TEXT               isVisited    INTEGER (0/1)
                               note         TEXT
```

`TRIPS` ||──o{ `PLACES` : "has" (CASCADE DELETE)

## Table: `trips`

| Column | Type | Constraint |
|--------|------|-----------|
| id | INTEGER | PRIMARY KEY AUTOINCREMENT |
| title | TEXT | NOT NULL |
| destination | TEXT | NOT NULL |
| startDate | TEXT | NOT NULL (YYYY-MM-DD) |
| endDate | TEXT | NOT NULL (YYYY-MM-DD) |
| budget | REAL | NOT NULL DEFAULT 0 |
| note | TEXT | NOT NULL DEFAULT '' |

## Table: `places`

| Column | Type | Constraint |
|--------|------|-----------|
| id | INTEGER | PRIMARY KEY AUTOINCREMENT |
| tripId | INTEGER | FK → trips(id) ON DELETE CASCADE |
| title | TEXT | NOT NULL |
| description | TEXT | NOT NULL |
| estimatedCost | REAL | NOT NULL DEFAULT 0 |
| category | TEXT | NOT NULL (PlaceCategory.name) |
| isVisited | INTEGER | NOT NULL (0 = false, 1 = true) |
| note | TEXT | NOT NULL |

Index: `idx_places_tripId` on `places(tripId)`

## PlaceCategory Values

`SIGHT`, `RESTAURANT`, `HOTEL`, `TRANSPORT`, `SHOPPING`, `ENTERTAINMENT`, `OTHER`

## Key Design Decisions

- **Dates stored as TEXT** (ISO 8601: `YYYY-MM-DD`) — Room has no native Date type
- **isVisited stored as INTEGER** — SQLite has no Boolean; Room maps `Boolean ↔ 0/1`
- **category stored as TEXT** — enum stored by `.name`, restored via `PlaceCategory.valueOf()` with `OTHER` fallback
- **CASCADE DELETE** — deleting a trip removes all its places at DB level
