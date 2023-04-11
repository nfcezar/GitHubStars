package com.example.feature.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.api.GitHubApi
import com.example.core.model.GitHubRepo
import com.example.core.model.GitHubUser
import com.example.feature.repository.RepoDetailsRepository
import com.example.feature.repository.SearchRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchRepository: SearchRepository,
    private val repoDetailsRepository: RepoDetailsRepository,
) : ViewModel() {

    private val api = GitHubApi

    var uiState: GitHubUiState by mutableStateOf(GitHubUiState.Loading)
        private set

    init {
        api.createInstance()
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                val response = searchRepository.fetchRemote()
                val repoList = searchRepository.getDataFromResponse(response)
                uiState = GitHubUiState.Success(repoList)

                val repoName = repoList.map {
                    it.name
                }.first()

                val ownerName = repoList.map {
                    it.owner.login
                }.first()

                val userResponse = repoDetailsRepository.fetchRemote(ownerName, repoName)
                repoDetailsRepository.getDataFromResponse(userResponse)

            } catch (e: Exception) {
                uiState = GitHubUiState.Error
            }
        }
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