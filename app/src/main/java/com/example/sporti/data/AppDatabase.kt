package com.example.sporti.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [WeightEntry::class, WorkoutEntry::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS workout_entries (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        sportId TEXT NOT NULL,
                        durationSeconds INTEGER NOT NULL,
                        exercisesCompleted INTEGER NOT NULL,
                        pointsEarned INTEGER NOT NULL,
                        timestamp INTEGER NOT NULL
                    )
                """)
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "sporti_database")
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
