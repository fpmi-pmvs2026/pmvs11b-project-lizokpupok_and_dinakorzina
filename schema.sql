-- Travel Planner — схема базы данных SQLite (Room v1)

CREATE TABLE trips (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    title       TEXT    NOT NULL,
    destination TEXT    NOT NULL,
    startDate   TEXT    NOT NULL,
    endDate     TEXT    NOT NULL,
    budget      REAL    NOT NULL DEFAULT 0,
    note        TEXT    NOT NULL DEFAULT ''
);

CREATE TABLE places (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    tripId        INTEGER NOT NULL,
    title         TEXT    NOT NULL,
    description   TEXT    NOT NULL DEFAULT '',
    estimatedCost REAL    NOT NULL DEFAULT 0,
    category      TEXT    NOT NULL DEFAULT 'OTHER',
    isVisited     INTEGER NOT NULL DEFAULT 0,
    note          TEXT    NOT NULL DEFAULT '',
    FOREIGN KEY (tripId) REFERENCES trips(id) ON DELETE CASCADE
);

CREATE INDEX idx_places_tripId ON places(tripId);
