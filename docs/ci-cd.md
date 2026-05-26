# CI/CD — GitHub Actions

File: `.github/workflows/android-ci.yml`

## Pipeline

```
push / PR → main, develop, feature/**, setup/**
                │
                ▼
        Job: build (ubuntu-latest)
        1. Checkout
        2. JDK 17
        3. chmod +x gradlew
        4. ./gradlew clean
        5. ./gradlew testDebugUnitTest  → artifact: unit-test-results
        6. ./gradlew assembleDebug      → artifact: app-debug.apk
                │
                ▼ (main branch only)
        Job: instrumented-tests
        Android Emulator API 29 (x86_64)
        ./gradlew connectedDebugAndroidTest → artifact: instrumented-test-results
```

## APK Artifact

After every successful build the APK is uploaded:
`app/build/outputs/apk/debug/app-debug.apk`

Download: **Actions → Run → Artifacts → app-debug**
