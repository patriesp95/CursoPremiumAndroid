package com.patrimesp.cursopremiumandroid.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.cursopremiumandroid.ui.theme.BackgroundApp
import com.patrimesp.cursopremiumandroid.ui.theme.ControlColor
import com.patrimesp.cursopremiumandroid.ui.theme.SecondaryText

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onBackSelected: () -> Unit
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

    MainContent(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(uiState: MainUiState) {
    Scaffold(
        containerColor = BackgroundApp, topBar = {
            TopAppBar(
                title = { Text("Busca tu chucho") }, navigationIcon = {
                    IconButton(onClick = { }) {

                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundApp,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(32.dp))
            when {
                uiState.isLoading -> {
                    LoadingMainState()
                }

                uiState.error != null -> {
                    ErrorMainState(uiState.error)
                }
            }
        }
    }

}

@Composable
fun LoadingMainState(){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = ControlColor)
    }
}

@Composable
fun ErrorMainState(error: String?){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(error.orEmpty(), color = SecondaryText)
    }
}