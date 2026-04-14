package com.example.sporti.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_entries")
data class WeightEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: Float,
    val timestamp: Long
)
