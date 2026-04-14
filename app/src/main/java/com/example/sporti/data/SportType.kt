package com.example.sporti.data

data class SportType(
    val id: String,
    val nameRes: Int,
    val icon: String,
    val color: Long,
    val exercises: List<Exercise>
)

data class Exercise(
    val nameRes: Int,
    val durationSeconds: Int = 30,
    val descriptionRes: Int = 0,
    val illustrationRes: Int = 0
)
