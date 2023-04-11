package com.example.feature.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.model.GitHubRepo
import com.example.feature.R
import com.example.feature.viewmodel.MainViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Home(
    gitHubUiState: MainViewModel.Companion.GitHubUiState,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (gitHubUiState) {
        is MainViewModel.Companion.GitHubUiState.Success -> SuccessGridScreen(
            gitHubUiState.repoList,
            modifier
        )
        is MainViewModel.Companion.GitHubUiState.Error -> ErrorScreen(
            action, modifier
        )
        is MainViewModel.Companion.GitHubUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun SuccessGridScreen(items: List<GitHubRepo>, modifier: Modifier) {
    LazyVerticalGrid(
        GridCells.Adaptive(150.dp),
        modifier.fillMaxWidth()
    ) {
        items(items, key = { item -> item.id }) { item ->
            CardSuccess(item)
        }
    }
}

@Composable
fun ErrorScreen(action: () -> Unit, modifier: Modifier) {
    Column(
        modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text("Failed to Load Data. Do you wanna try again?")
        Button(onClick = action) {
            Text("Try Again")
        }
    }
}

@Composable
fun CardSuccess(
    gitHubRepo: GitHubRepo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .padding(8.dp)
            .wrapContentWidth(Alignment.Start)
            .wrapContentHeight(Alignment.Top)
            .fillMaxSize()
            .aspectRatio(1f),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier
                .padding(10.dp)
                .fillMaxSize(),

            ) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(gitHubRepo.owner.avatarUrl)
                    .build(),
                contentDescription = "GitHub Avatar",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 50.dp),
            )
            Spacer(modifier = modifier.size(12.dp))

            Text(stringResource(id = R.string.owner_name) + gitHubRepo.owner.login)
            Text(stringResource(id = R.string.repo_name) + gitHubRepo.name)
            Text(stringResource(id = R.string.stars_number) + gitHubRepo.stars.toString())
            Text(stringResource(id = R.string.forks_number) + gitHubRepo.forks.toString())
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(
                R.drawable.loading_img
            ),
            contentDescription = "Loading"
        )
    }
}


