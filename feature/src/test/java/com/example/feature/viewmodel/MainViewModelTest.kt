package com.example.feature.viewmodel

import com.example.core.model.GitHubRepo
import com.example.core.model.GitHubUser
import com.example.core.repository.RepoDetailsRepository
import com.example.core.repository.SearchRepository
import com.example.core.response.OwnerRepoResponse
import com.example.core.response.SearchResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var searchRepository: SearchRepository

    @MockK
    private lateinit var detailsRepository: RepoDetailsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel = MainViewModel(
            searchRepository,
            detailsRepository
        )
    }

    @Test
    fun `when get items are loading from repo should set loading state`() = runBlocking {
        viewModel.getData()

        assertEquals(
            viewModel.uiState,
            MainViewModel.Companion.GitHubUiState.Loading
        )
    }


    @Test
    fun `when get items from repo is success should set success state`() = runBlocking {
        val searchResponse = SearchResponse(
            1,
            mockListRepo
        )
        val searchResponseSuccess = Response.success(searchResponse)

        val ownerRepoResponse = OwnerRepoResponse(
            1,
            mockListOwner
        )
        val ownerRepoResponseSuccess = Response.success(ownerRepoResponse)

        coEvery { searchRepository.fetchRemote() } returns searchResponseSuccess
        coEvery { searchRepository.getDataFromResponse(searchResponseSuccess) } returns mockListRepo
        coEvery { detailsRepository.fetchRemote(any(), any()) } returns ownerRepoResponseSuccess
        coEvery { detailsRepository.getDataFromResponse(ownerRepoResponseSuccess) } returns mockListOwner

        viewModel.getData()

        assertEquals(viewModel.uiState, MainViewModel.Companion.GitHubUiState.Success(mockListRepo))
    }


    @Test
    fun `when get items from repo is unsuccessful should set error state`() = runBlocking {
        coEvery { searchRepository.fetchRemote() }.throws(IOException())

        viewModel.getData()

        assertEquals(
            viewModel.uiState,
            MainViewModel.Companion.GitHubUiState.Error
        )
    }

    private val owner = GitHubUser(
        login = "John",
        avatarUrl = ""
    )
    private val mockListRepo = listOf(
        GitHubRepo(
            id = 100,
            name = "Learning Developement",
            stars = 34,
            forks = 11,
            owner
        )
    )
    private val mockListOwner = listOf(owner)
}

