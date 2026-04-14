package com.example.sporti.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporti.data.AppDatabase
import com.example.sporti.data.RewardsRepository
import com.example.sporti.data.WorkoutEntry
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RewardsViewModel(application: Application) : AndroidViewModel(application) {
    private val workoutDao = AppDatabase.getDatabase(application).workoutDao()

    val totalWorkouts: StateFlow<Int> = workoutDao.getTotalWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalPoints: StateFlow<Int> = workoutDao.getTotalPoints()
        .map { it ?: 0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val uniqueSports: StateFlow<Int> = workoutDao.getUniqueSportsCompleted()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val workoutDays: StateFlow<List<String>> = workoutDao.getWorkoutDays()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentStreak: StateFlow<Int> = workoutDays.map { days ->
        calculateStreak(days)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val allWorkouts: StateFlow<List<WorkoutEntry>> = workoutDao.getAllEntries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun logWorkout(sportId: String, durationSeconds: Int, exercisesCompleted: Int, totalExercises: Int) {
        viewModelScope.launch {
            val streak = currentStreak.value
            val points = RewardsRepository.calculatePoints(exercisesCompleted, totalExercises, streak)
            workoutDao.insertWorkout(
                WorkoutEntry(
                    sportId = sportId,
                    durationSeconds = durationSeconds,
                    exercisesCompleted = exercisesCompleted,
                    pointsEarned = points,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    private fun calculateStreak(days: List<String>): Int {
        if (days.isEmpty()) return 0
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val cal = Calendar.getInstance()
        val today = sdf.format(cal.time)
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = sdf.format(cal.time)

        // Streak must include today or yesterday
        if (days.first() != today && days.first() != yesterday) return 0

        var streak = 1
        for (i in 1 until days.size) {
            val prev = sdf.parse(days[i - 1]) ?: break
            val curr = sdf.parse(days[i]) ?: break
            val diffDays = ((prev.time - curr.time) / (1000 * 60 * 60 * 24)).toInt()
            if (diffDays == 1) {
                streak++
            } else {
                break
            }
        }
        return streak
    }
}
