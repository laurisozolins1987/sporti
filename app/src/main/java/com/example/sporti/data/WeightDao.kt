package com.example.sporti.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
    @Query("SELECT * FROM weight_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<WeightEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(entry: WeightEntry)
}
