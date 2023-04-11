package com.example.feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.GitHubRepo
import com.example.core.repository.RepoDetailsRepository
import com.example.core.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainViewModel(
    private val searchRepository: SearchRepository,
    private val repoDetailsRepository: RepoDetailsRepository,
) : ViewModel() {

    var uiState: GitHubUiState by mutableStateOf(GitHubUiState.Loading)
        private set

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = searchRepository.fetchRemote()
                val repoList = searchRepository.getDataFromResponse(response)

                val repoName = repoList.map {
                    it.name
                }.first()

                val ownerName = repoList.map {
                    it.owner.login
                }.first()

                val userResponse = repoDetailsRepository.fetchRemote(ownerName, repoName)
                repoDetailsRepository.getDataFromResponse(userResponse)

                withContext(Dispatchers.Main) {
                    setState(GitHubUiState.Success(repoList))
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    setState(GitHubUiState.Error)
                }
            }
        }
    }

    private fun setState(value: GitHubUiState) {
        uiState = value
    }

    companion object {
        interface GitHubUiState {
            data class Success(
                val repoList: List<GitHubRepo>
            ) : GitHubUiState

            object Error : GitHubUiState
            object Loading : GitHubUiState
        }
    }
}