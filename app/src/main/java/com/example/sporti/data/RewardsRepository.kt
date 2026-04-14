package com.example.sporti.data

import com.example.sporti.R

object RewardsRepository {

    // Points awarded per completed workout
    const val BASE_POINTS = 50
    const val STREAK_BONUS_MULTIPLIER = 10 // extra points per streak day
    const val FULL_WORKOUT_BONUS = 25 // extra for completing all exercises

    fun calculatePoints(exercisesCompleted: Int, totalExercises: Int, streakDays: Int): Int {
        var points = BASE_POINTS
        // Full completion bonus
        if (exercisesCompleted >= totalExercises) {
            points += FULL_WORKOUT_BONUS
        }
        // Streak multiplier
        points += streakDays * STREAK_BONUS_MULTIPLIER
        return points
    }

    fun getAllAchievements(): List<Achievement> = listOf(
        // Workout count achievements
        Achievement("first_workout", R.string.ach_first_workout, R.string.ach_first_workout_desc, "🎯", 0xFF42A5F5, 1, AchievementType.TOTAL_WORKOUTS),
        Achievement("five_workouts", R.string.ach_five_workouts, R.string.ach_five_workouts_desc, "⭐", 0xFFFFA726, 5, AchievementType.TOTAL_WORKOUTS),
        Achievement("ten_workouts", R.string.ach_ten_workouts, R.string.ach_ten_workouts_desc, "🔥", 0xFFEF5350, 10, AchievementType.TOTAL_WORKOUTS),
        Achievement("twenty_five_workouts", R.string.ach_25_workouts, R.string.ach_25_workouts_desc, "💎", 0xFF7E57C2, 25, AchievementType.TOTAL_WORKOUTS),
        Achievement("fifty_workouts", R.string.ach_50_workouts, R.string.ach_50_workouts_desc, "👑", 0xFFFFD700, 50, AchievementType.TOTAL_WORKOUTS),
        Achievement("hundred_workouts", R.string.ach_100_workouts, R.string.ach_100_workouts_desc, "🏆", 0xFFFF6F00, 100, AchievementType.TOTAL_WORKOUTS),

        // Points achievements
        Achievement("points_500", R.string.ach_500_points, R.string.ach_500_points_desc, "💰", 0xFF66BB6A, 500, AchievementType.TOTAL_POINTS),
        Achievement("points_2000", R.string.ach_2000_points, R.string.ach_2000_points_desc, "💎", 0xFF26C6DA, 2000, AchievementType.TOTAL_POINTS),
        Achievement("points_5000", R.string.ach_5000_points, R.string.ach_5000_points_desc, "🌟", 0xFFAB47BC, 5000, AchievementType.TOTAL_POINTS),
        Achievement("points_10000", R.string.ach_10000_points, R.string.ach_10000_points_desc, "💫", 0xFFFFD700, 10000, AchievementType.TOTAL_POINTS),

        // Streak achievements
        Achievement("streak_3", R.string.ach_streak_3, R.string.ach_streak_3_desc, "🔥", 0xFFFF7043, 3, AchievementType.STREAK_DAYS),
        Achievement("streak_7", R.string.ach_streak_7, R.string.ach_streak_7_desc, "🔥", 0xFFEF5350, 7, AchievementType.STREAK_DAYS),
        Achievement("streak_14", R.string.ach_streak_14, R.string.ach_streak_14_desc, "⚡", 0xFFFFA726, 14, AchievementType.STREAK_DAYS),
        Achievement("streak_30", R.string.ach_streak_30, R.string.ach_streak_30_desc, "🌋", 0xFFD32F2F, 30, AchievementType.STREAK_DAYS),

        // Variety achievements
        Achievement("explorer_3", R.string.ach_explorer_3, R.string.ach_explorer_3_desc, "🧭", 0xFF5C6BC0, 3, AchievementType.UNIQUE_SPORTS),
        Achievement("explorer_6", R.string.ach_explorer_6, R.string.ach_explorer_6_desc, "🗺️", 0xFF26A69A, 6, AchievementType.UNIQUE_SPORTS),
        Achievement("explorer_12", R.string.ach_explorer_12, R.string.ach_explorer_12_desc, "🌍", 0xFF00E676, 12, AchievementType.UNIQUE_SPORTS),
    )

    fun getLevel(totalPoints: Int): Pair<Int, String> {
        return when {
            totalPoints >= 10000 -> 10 to "legend"
            totalPoints >= 7500 -> 9 to "champion"
            totalPoints >= 5000 -> 8 to "master"
            totalPoints >= 3500 -> 7 to "expert"
            totalPoints >= 2500 -> 6 to "advanced"
            totalPoints >= 1500 -> 5 to "intermediate"
            totalPoints >= 1000 -> 4 to "dedicated"
            totalPoints >= 500 -> 3 to "active"
            totalPoints >= 200 -> 2 to "beginner"
            else -> 1 to "newbie"
        }
    }

    fun getPointsForNextLevel(totalPoints: Int): Int {
        return when {
            totalPoints >= 10000 -> 10000
            totalPoints >= 7500 -> 10000
            totalPoints >= 5000 -> 7500
            totalPoints >= 3500 -> 5000
            totalPoints >= 2500 -> 3500
            totalPoints >= 1500 -> 2500
            totalPoints >= 1000 -> 1500
            totalPoints >= 500 -> 1000
            totalPoints >= 200 -> 500
            else -> 200
        }
    }

    fun getLevelTitleRes(levelName: String): Int {
        return when (levelName) {
            "legend" -> R.string.level_legend
            "champion" -> R.string.level_champion
            "master" -> R.string.level_master
            "expert" -> R.string.level_expert
            "advanced" -> R.string.level_advanced
            "intermediate" -> R.string.level_intermediate
            "dedicated" -> R.string.level_dedicated
            "active" -> R.string.level_active
            "beginner" -> R.string.level_beginner
            else -> R.string.level_newbie
        }
    }
}
