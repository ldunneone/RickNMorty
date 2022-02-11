package com.example.rickmorty.network

class MainRepository constructor(private val retrofitService: RetrofitService){
    suspend fun getAllResults() = retrofitService.getAllCharacters()
}