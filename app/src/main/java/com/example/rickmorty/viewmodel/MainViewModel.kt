package com.example.rickmorty.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.data.Results
import com.example.rickmorty.network.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) :ViewModel(){

    val errorMessage = MutableLiveData<String>()
    val charList = MutableLiveData<List<Results>>()
    var job: Job? = null
//    val exceptionHandler = CoroutineExceptionHandler { _, throwable
//        ->
//        onError("Exception handled: ${throwable.localizedMessage}")
//        Log.d("Error", "Exception Handled ${throwable.localizedMessage}")
//    }
    val loading = MutableLiveData<Boolean>()

    fun getAllResults() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllResults()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    charList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                    Log.d("Error", "${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}