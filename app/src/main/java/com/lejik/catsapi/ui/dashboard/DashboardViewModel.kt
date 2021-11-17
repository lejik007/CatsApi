package com.lejik.catsapi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lejik.catsapi.network.CatsApi
import com.lejik.catsapi.network.CatsPhoto
import kotlinx.coroutines.launch

enum class CatsApiStatus { LOADING, ERROR, DONE }

class DashboardViewModel : ViewModel() {

    private val _status = MutableLiveData<CatsApiStatus>()
    val status: LiveData<CatsApiStatus> = _status
    private val _photos = MutableLiveData<List<CatsPhoto>>()
    val photos: LiveData<List<CatsPhoto>> = _photos
    init {
        getMarsPhotos()
    }
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = CatsApiStatus.LOADING
            try {
                _photos.value = CatsApi.retrofitService.getPhotos()
                _status.value = CatsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text
}