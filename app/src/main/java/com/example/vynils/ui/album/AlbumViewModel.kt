package com.example.vynils.ui.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vynils.model.Album
import com.example.vynils.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AlbumRepository()

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val fetchedAlbums = repository.fetchAlbums(getApplication())
                _albums.value = fetchedAlbums
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}
