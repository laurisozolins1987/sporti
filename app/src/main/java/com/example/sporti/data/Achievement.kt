package com.example.sporti.data

data class Achievement(
    val id: String,
    val titleRes: Int,
    val descriptionRes: Int,
    val icon: String,
    val color: Long,
    val targetValue: Int,
    val type: AchievementType
)

enum class AchievementType {
    TOTAL_WORKOUTS,
    TOTAL_POINTS,
    STREAK_DAYS,
    UNIQUE_SPORTS,
    SINGLE_SPORT_COUNT
}
