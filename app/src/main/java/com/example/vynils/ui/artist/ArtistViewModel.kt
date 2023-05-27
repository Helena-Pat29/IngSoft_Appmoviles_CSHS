package com.example.vynils.ui.artist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vynils.model.Artist
import com.example.vynils.repository.ArtistRepository
import kotlinx.coroutines.launch

class ArtistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ArtistRepository()

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>>
        get() = _artists

    private val _error = MutableLiveData<Throwable>()

    init {
        fetchArtists()
    }

    private fun fetchArtists() {
        viewModelScope.launch {
            try {
                val fetchedArtists = repository.fetchArtists(getApplication())
                _artists.value = fetchedArtists
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}