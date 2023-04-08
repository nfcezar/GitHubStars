package com.example.githubstars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feature.presentation.viewmodel.MainViewModel
import com.example.githubstars.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}