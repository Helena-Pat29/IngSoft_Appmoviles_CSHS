package com.example.vynils.ui.prize

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vynils.repository.PrizeRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class PrizeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PrizeRepository()

    private var _prizeId = 0
    val prizeId: Int
        get() = _prizeId

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun postPrize(prize: JSONObject) {
        viewModelScope.launch {
            try {
                val prizeId = repository.postPrize(getApplication(), prize)
                _prizeId = prizeId
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}