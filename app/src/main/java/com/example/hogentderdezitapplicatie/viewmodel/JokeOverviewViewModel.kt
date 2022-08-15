package com.example.hogentderdezitapplicatie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hogentderdezitapplicatie.api.JokeApi
import com.example.hogentderdezitapplicatie.api.JokeProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeOverviewViewModel :ViewModel(){


    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getJokeProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    // TODO (07) Update getMarsRealEstateProperties to handle List<MarsProperty>
     fun getJokeProperties() {
        Log.d("in getjokers","from jokeviewmodel")
        coroutineScope.launch {
            var getPropertiesDeferred = JokeApi.retrofitService.getProperties()
            Log.d("in getjokers","2de")
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "${listResult.joke}"
                Log.d("in viewModel","${_response.value}")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}