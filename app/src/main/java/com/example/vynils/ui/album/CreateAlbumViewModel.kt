package com.example.vynils.ui.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vynils.DTO.CreateAlbumDTO
import com.example.vynils.model.Album
import com.example.vynils.repository.AlbumRepository
import kotlinx.coroutines.launch

class CreateAlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val albumRepository = AlbumRepository()

    private val _createAlbumResult = MutableLiveData<Album>()
    val createAlbumResult: LiveData<Album> = _createAlbumResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun createAlbum(album: CreateAlbumDTO) {
        viewModelScope.launch {
            try {
                val result = albumRepository.createAlbum(getApplication(), album)
                _createAlbumResult.value = result
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }
}
