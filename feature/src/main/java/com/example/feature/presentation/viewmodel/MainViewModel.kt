package com.example.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.repository.GitHubRepository
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val repository: GitHubRepository
) : ViewModel() {

    init {
        //setLoadingState
    }

    fun getData() {
        viewModelScope.launch {
            try {
                repository.getData()
                //setSuccessState
            } catch (IO: IOException) {
                //setErrorState
            }
        }
    }
}