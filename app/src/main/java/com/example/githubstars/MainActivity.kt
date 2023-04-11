package com.example.githubstars

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.presentation.ui.screens.Home
import com.example.feature.presentation.viewmodel.MainViewModel
import com.example.githubstars.ui.theme.GitHubStarsTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubStarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GitHubStarsApp()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun GitHubStarsApp(modifier: Modifier = Modifier) {
        Scaffold(
            modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    { Text(text = "GitHub Repository") }
                )
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Home(gitHubUiState = viewModel.uiState,
                    action = { viewModel.getData() }
                )
            }
        }
    }
}