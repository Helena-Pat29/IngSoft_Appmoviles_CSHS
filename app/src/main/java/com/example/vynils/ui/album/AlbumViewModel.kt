package com.example.vynils.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynils.model.Album
import com.example.vynils.repository.AlbumRepository

class AlbumViewModel : ViewModel() {
    private val repository = AlbumRepository()

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        val albumList = repository.fetchAlbums()
        _albums.value = albumList
    }
}
