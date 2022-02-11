package com.example.rickmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.network.MainRepository
import com.example.rickmorty.network.RetrofitService
import com.example.rickmorty.viewmodel.MainViewModel
import com.example.rickmorty.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.charList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setCharacterList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })

        viewModel.getAllResults()
    }
}