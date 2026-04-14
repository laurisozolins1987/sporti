package com.example.sporti.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_entries")
data class WorkoutEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sportId: String,
    val durationSeconds: Int,
    val exercisesCompleted: Int,
    val pointsEarned: Int,
    val timestamp: Long
)
