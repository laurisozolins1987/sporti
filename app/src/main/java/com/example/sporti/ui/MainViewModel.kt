package com.example.sporti.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporti.data.AppDatabase
import com.example.sporti.data.WeightEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).weightDao()

    val weightEntries: StateFlow<List<WeightEntry>> = dao.getAllEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addWeight(weight: Float) {
        viewModelScope.launch {
            dao.insertWeight(
                WeightEntry(
                    weight = weight,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}
