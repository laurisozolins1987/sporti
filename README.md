<p align="center">
  <img src="app/src/main/res/drawable/ic_launcher_foreground.xml" width="120" alt="Sportiņš Logo"/>
</p>

<h1 align="center">🏋️ Sportiņš</h1>

<p align="center">
  <strong>Your personal fitness companion — built with Jetpack Compose</strong><br/>
  <em>Timed workouts • 12 sport types • Reward system • Weight tracking</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/Kotlin-2.1.0-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-Material3-4285F4?logo=jetpackcompose&logoColor=white" alt="Compose"/>
  <img src="https://img.shields.io/badge/Min_SDK-24-blue" alt="Min SDK"/>
  <img src="https://img.shields.io/badge/License-MIT-green" alt="License"/>
</p>

---

## 📱 About

**Sportiņš** (Latvian for "little sports") is a modern Android fitness app that guides you through timed exercise routines across 12 different sport types. Complete workouts, earn points, unlock achievements, and track your progress — all with a beautiful dark-first Material 3 design.

---

## ✨ Features

### 🏠 Dashboard
- Quick stats overview (workouts completed, weight, streak)
- Featured workout highlight
- 2-column sport selection grid
- Weight tracker with interactive chart
- One-tap access to rewards

### ⏱️ Guided Workouts
- Step-by-step exercise timer
- Visual progress bar across exercises
- Animated exercise transitions
- Start / Pause / Reset / Skip controls
- Points awarded on completion

### 🏆 Rewards & Achievements
| Feature | Description |
|---------|-------------|
| **Points System** | Earn 50+ pts per workout |
| **Streak Bonus** | +10 pts per streak day |
| **Full Workout Bonus** | +25 pts for completing all exercises |
| **10 Levels** | Newbie → Legend (based on total points) |
| **17 Achievements** | Workout count, points, streaks, variety |
| **Progress Tracking** | Visual progress bars on each achievement |

### ⚖️ Weight Tracker
- Log daily weight
- Beautiful curve chart visualization
- Latest weight shown on dashboard

### 🌐 Localization
- 🇬🇧 English
- 🇱🇻 Latvian (Latviešu)

---

## 🏅 Sport Types (12)

Each sport comes with its own color theme, icon, and curated exercise set:

| # | Sport | Icon | Exercises | Duration | What You'll Do |
|---|-------|------|-----------|----------|----------------|
| 1 | **Morning Routine** | ☀️ | 7 | ~4 min | Neck circles, arm circles, jumping jacks, squats, lunges, cat-cow stretch |
| 2 | **Running** | 🏃 | 6 | ~4 min | Warm-up jog, high knees, butt kicks, sprint intervals, side shuffle |
| 3 | **Yoga** | 🧘 | 8 | ~5 min | Mountain pose, downward dog, warrior I & II, tree pose, cobra, savasana |
| 4 | **HIIT** | 🔥 | 8 | ~4 min | Burpees, mountain climbers, jump squats, push-ups, tuck jumps |
| 5 | **Strength** | 💪 | 8 | ~6 min | Push-ups, squats, lunges, plank, tricep dips, glute bridge, wall sit |
| 6 | **Cardio** | ❤️ | 7 | ~4 min | Jumping jacks, high knees, skaters, star jumps, speed skipping |
| 7 | **Boxing** | 🥊 | 7 | ~4 min | Jab-cross, hook-uppercut, shadow boxing, speed punches, footwork |
| 8 | **Flexibility** | 🤸 | 7 | ~4 min | Hamstring, quad, hip flexor, shoulder stretches, pigeon pose |
| 9 | **Core** | 🎯 | 8 | ~4 min | Plank, bicycle crunches, russian twists, leg raises, flutter kicks |
| 10 | **Evening Stretch** | 🌙 | 6 | ~5 min | Gentle stretches, child's pose, savasana |
| 11 | **Cycling** | 🚴 | 5 | ~5 min | Warm-up ride, hill climb sim, sprint intervals, endurance ride |
| 12 | **Swimming** | 🏊 | 6 | ~5 min | Arm circles, freestyle drills, backstroke, treading water |

### Exercise Visual Guide

Each exercise has a set duration (25-90 seconds) and the app counts down for you:

```
┌──────────────────────────────────────┐
│        🏋️  Push-ups                  │
│                                      │
│           ┌─────────┐               │
│           │         │               │
│           │   45    │  ← Timer      │
│           │         │               │
│           └─────────┘               │
│                                      │
│   ████████████░░░░░░  3 of 8        │
│                                      │
│       [  PAUSE  ]  [ Skip → ]       │
└──────────────────────────────────────┘
```

---

## 🏆 Rewards System Deep Dive

### Points Calculation
```
Points = Base(50) + FullWorkoutBonus(25) + StreakBonus(streak × 10)
```

| Scenario | Points Earned |
|----------|---------------|
| First workout, no streak | 75 pts |
| Full workout, 3-day streak | 105 pts |
| Full workout, 7-day streak | 145 pts |
| Full workout, 30-day streak | 375 pts |

### Level System
| Level | Title | Points Required |
|-------|-------|-----------------|
| 1 | 🆕 Newbie | 0 |
| 2 | 🌱 Beginner | 200 |
| 3 | ⚡ Active | 500 |
| 4 | 🎯 Dedicated | 1,000 |
| 5 | 💪 Intermediate | 1,500 |
| 6 | 🔥 Advanced | 2,500 |
| 7 | ⭐ Expert | 3,500 |
| 8 | 🏅 Master | 5,000 |
| 9 | 👑 Champion | 7,500 |
| 10 | 🏆 Legend | 10,000 |

### Achievements (17 total)

**Workout Milestones:**
| Achievement | Target | Icon |
|-------------|--------|------|
| First Steps | 1 workout | 🎯 |
| Getting Started | 5 workouts | ⭐ |
| On Fire | 10 workouts | 🔥 |
| Workout Warrior | 25 workouts | 💎 |
| Half Century | 50 workouts | 👑 |
| Century Club | 100 workouts | 🏆 |

**Point Milestones:**
| Achievement | Target | Icon |
|-------------|--------|------|
| Point Collector | 500 pts | 💰 |
| Point Master | 2,000 pts | 💎 |
| Point Legend | 5,000 pts | 🌟 |
| Point God | 10,000 pts | 💫 |

**Streak Milestones:**
| Achievement | Target | Icon |
|-------------|--------|------|
| Warming Up | 3 days | 🔥 |
| Week Warrior | 7 days | 🔥 |
| Two Week Titan | 14 days | ⚡ |
| Monthly Monster | 30 days | 🌋 |

**Variety/Explorer:**
| Achievement | Target | Icon |
|-------------|--------|------|
| Explorer | 3 sport types | 🧭 |
| Adventurer | 6 sport types | 🗺️ |
| Master of All | 12 sport types | 🌍 |

---

## 🏗️ Architecture & Tech Stack

```
app/
├── src/main/java/com/example/sporti/
│   ├── MainActivity.kt              # Entry point + Navigation
│   ├── data/
│   │   ├── AppDatabase.kt           # Room database (v2)
│   │   ├── WeightEntry.kt           # Weight tracking entity
│   │   ├── WeightDao.kt             # Weight CRUD operations
│   │   ├── WorkoutEntry.kt          # Workout log entity
│   │   ├── WorkoutDao.kt            # Workout queries & stats
│   │   ├── SportType.kt             # Sport & Exercise models
│   │   ├── SportsRepository.kt      # All 12 sport definitions
│   │   ├── Achievement.kt           # Achievement data model
│   │   └── RewardsRepository.kt     # Points, levels, achievements
│   └── ui/
│       ├── DashboardScreen.kt       # Home screen
│       ├── WorkoutScreen.kt         # Guided workout timer
│       ├── RewardsScreen.kt         # Achievements & level display
│       ├── MainViewModel.kt         # Weight tracking VM
│       ├── RewardsViewModel.kt      # Rewards & stats VM
│       └── theme/
│           ├── Color.kt             # Color palette
│           ├── Theme.kt             # Dark/Light themes
│           └── Type.kt              # Typography
└── src/main/res/
    ├── drawable/                     # Sport vector illustrations (12)
    ├── values/strings.xml            # English strings (140+)
    └── values-lv/strings.xml         # Latvian strings
```

### Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Kotlin** | 2.1.0 | Primary language |
| **Jetpack Compose** | Material 3 | Declarative UI |
| **Room** | — | Local SQLite database |
| **Navigation Compose** | — | Screen navigation |
| **KSP** | — | Annotation processing |
| **Coroutines + Flow** | — | Async & reactive data |
| **ViewModel** | AndroidX | State management |

### Design Principles
- **Dark-first** design with neon green accent
- **Material 3** color system with dynamic theming
- **Spring animations** for press effects
- **Smooth transitions** between exercises
- **Curved charts** for weight data visualization

---

## 🚀 Getting Started

### Prerequisites
- Android Studio **Hedgehog** (2023.1.1) or later
- JDK 11+
- Android SDK 35

### Build & Run

```bash
# Clone the repository
git clone https://github.com/laurisozolins1987/sporti.git
cd sporti

# Build debug APK
./gradlew assembleDebug

# Install on connected device/emulator
./gradlew installDebug

# Run tests
./gradlew test
```

### Open in Android Studio
1. Open Android Studio
2. File → Open → select the `sporti` folder
3. Wait for Gradle sync
4. Run on emulator or device (API 24+)

---

## 📂 Database Schema

### `weight_entries`
| Column | Type | Description |
|--------|-------|-------------|
| `id` | INTEGER (PK, auto) | Unique ID |
| `weight` | REAL | Weight in kg |
| `timestamp` | INTEGER | Unix timestamp (ms) |

### `workout_entries`
| Column | Type | Description |
|--------|------|-------------|
| `id` | INTEGER (PK, auto) | Unique ID |
| `sportId` | TEXT | Sport type identifier |
| `durationSeconds` | INTEGER | Total workout duration |
| `exercisesCompleted` | INTEGER | Number of exercises done |
| `pointsEarned` | INTEGER | Points awarded |
| `timestamp` | INTEGER | Unix timestamp (ms) |

---

## 🎨 Color Palette

| Color | Hex | Usage |
|-------|-----|-------|
| Neon Green | `#00E676` | Primary accent |
| Deep Background | `#0F0F23` | Dark theme background |
| Card Dark | `#1E1E3A` | Card surfaces |
| Accent Blue | `#448AFF` | Secondary elements |
| Accent Purple | `#AB47BC` | Tertiary elements |

### Sport Colors
| Sport | Color |
|-------|-------|
| Morning Routine | `#FFB74D` 🟠 |
| Running | `#42A5F5` 🔵 |
| Yoga | `#AB47BC` 🟣 |
| HIIT | `#EF5350` 🔴 |
| Strength | `#66BB6A` 🟢 |
| Cardio | `#EC407A` 💗 |
| Boxing | `#FF7043` 🟠 |
| Flexibility | `#26C6DA` 🩵 |
| Core | `#FFA726` 🟡 |
| Evening Stretch | `#7E57C2` 🟣 |
| Cycling | `#5C6BC0` 🔵 |
| Swimming | `#29B6F6` 🩵 |

---

## 🛠️ Development

### Adding a New Sport Type

1. Add string resources to `values/strings.xml` and `values-lv/strings.xml`
2. Create a vector drawable in `res/drawable/ic_sport_<name>.xml`
3. Add to `SportsRepository.kt`:

```kotlin
SportType(
    id = "new_sport",
    nameRes = R.string.sport_new,
    icon = "🎾",
    color = 0xFFXXXXXX,
    exercises = listOf(
        Exercise(R.string.ex_name, durationSeconds = 30),
        // ... more exercises
    )
)
```

### Adding New Achievements

Add to `RewardsRepository.getAllAchievements()`:

```kotlin
Achievement(
    id = "unique_id",
    titleRes = R.string.ach_title,
    descriptionRes = R.string.ach_desc,
    icon = "🎯",
    color = 0xFF42A5F5,
    targetValue = 10,
    type = AchievementType.TOTAL_WORKOUTS
)
```

### Database Migrations

When adding new entities, increment version in `AppDatabase.kt` and add a migration:

```kotlin
private val MIGRATION_X_Y = object : Migration(X, Y) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ...")
    }
}
```

---

## 📸 Screenshots

> Screenshots are taken from a Pixel 7 emulator running API 35, dark mode.

| Dashboard | Workout | Rewards |
|-----------|---------|---------|
| Sport grid with stats | Timer with exercise guide | Levels & achievements |

---

## 🤝 Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

<p align="center">
  Made with 💚 in Latvia 🇱🇻
</p>
