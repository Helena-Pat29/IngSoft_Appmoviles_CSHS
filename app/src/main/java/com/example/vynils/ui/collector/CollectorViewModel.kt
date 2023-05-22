package com.example.vynils.ui.collector

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vynils.model.Collector
import com.example.vynils.repository.CollectorRepository
import kotlinx.coroutines.launch

class CollectorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CollectorRepository()

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>>
        get() = _collectors

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    init {
        fetchCollectors()
    }

    private fun fetchCollectors() {
        viewModelScope.launch {
            try {
                val fetchedCollectors = repository.fetchCollectors(getApplication())
                Log.d("ResponseCollectors", "API call with $fetchedCollectors")
                _collectors.value = fetchedCollectors
            } catch (e: Exception) {
                Log.d("ErrorCollectors", "API call with $e")
                _error.value = e
            }
        }
    }
}
