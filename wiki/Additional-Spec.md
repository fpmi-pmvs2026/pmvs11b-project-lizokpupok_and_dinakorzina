# Additional Specification

## Non-Functional Requirements

| ID | Requirement |
|----|-------------|
| NFR-01 | App launches in < 2 s on API 29+ device |
| NFR-02 | DB operations run on `Dispatchers.IO`, never block UI thread |
| NFR-03 | Network requests run on `Dispatchers.IO` with coroutines |
| NFR-04 | App works offline (country info shows error, trips still available) |
| NFR-05 | Cascade delete — removing a trip removes all its places automatically |
| NFR-06 | minSdk 26, targetSdk / compileSdk 36 |

## REST Countries API

**Base URL:** `https://restcountries.com/v3.1/`

**Endpoint used:**
```
GET /name/{name}?fields=name,capital,region,currencies,flags,population
```

**Response fields used:**

| Field | Mapped to |
|-------|-----------|
| `name.common` | `DestinationInfo.country` |
| `capital[0]` | `DestinationInfo.capital` |
| `region` | `DestinationInfo.region` |
| `currencies.*` | `DestinationInfo.currency` ("Name (Symbol)") |
| `population` | `DestinationInfo.population` |
| `flags.png` | `DestinationInfo.flagUrl` |

**Error handling:** `Result<DestinationInfo>` — `Loading / Success / Error` states in ViewModel.

## CI/CD

See [ci-cd docs](../docs/ci-cd.md) or the [CI-CD wiki page](ci-cd).

Pipeline triggers on push/PR to: `main`, `develop`, `feature/**`, `setup/**`

Artifacts published per run:
- `unit-test-results` — JUnit XML
- `app-debug.apk` — installable APK
- `instrumented-test-results` — only on `main`
