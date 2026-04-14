package com.example.sporti.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<WorkoutEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(entry: WorkoutEntry)

    @Query("SELECT COUNT(*) FROM workout_entries")
    fun getTotalWorkouts(): Flow<Int>

    @Query("SELECT SUM(pointsEarned) FROM workout_entries")
    fun getTotalPoints(): Flow<Int?>

    @Query("SELECT COUNT(DISTINCT sportId) FROM workout_entries")
    fun getUniqueSportsCompleted(): Flow<Int>

    @Query("SELECT sportId, COUNT(*) as count FROM workout_entries GROUP BY sportId ORDER BY count DESC LIMIT 1")
    fun getFavoriteSport(): Flow<FavoriteSport?>

    @Query("SELECT COUNT(*) FROM workout_entries WHERE sportId = :sportId")
    fun getWorkoutCountForSport(sportId: String): Flow<Int>

    @Query("""
        SELECT DISTINCT date(timestamp / 1000, 'unixepoch', 'localtime') as day 
        FROM workout_entries 
        ORDER BY day DESC
    """)
    fun getWorkoutDays(): Flow<List<String>>
}

data class FavoriteSport(
    val sportId: String,
    val count: Int
)
