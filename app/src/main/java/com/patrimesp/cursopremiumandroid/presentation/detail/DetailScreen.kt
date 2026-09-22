package com.patrimesp.cursopremiumandroid.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.patrimesp.cursopremiumandroid.R
import com.patrimesp.cursopremiumandroid.ui.theme.BackgroundApp
import com.patrimesp.cursopremiumandroid.ui.theme.ControlColor
import com.patrimesp.cursopremiumandroid.ui.theme.SecondaryText

@Composable
fun DetailScreen(
    id: Int,
    onBackPressed: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(id) {
        detailViewModel.loadDogById(id)
    }
    DetailContent(uiState, onBackPressed)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(uiState: DetailUiState, onBackPressed: () -> Unit) {
    Scaffold(
        containerColor = BackgroundApp,
        topBar = {
            TopAppBar(
                title = { Text(uiState.dog?.name ?: "Detalle del perro") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(painterResource(R.drawable.ic_back), contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundApp,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> Box(
                Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ControlColor)
            }

            uiState.error != null -> Box(
                Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.error, color = SecondaryText)
            }

            uiState.dog != null -> {
                val dog = uiState.dog
                Column(
                    Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = dog.image,
                        contentDescription = dog.name,
                        modifier = Modifier.fillMaxWidth().height(240.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(dog.name, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text("${dog.breed} · ${dog.age} años", color = SecondaryText)
                    Text(dog.description, color = Color.White)
                    Text("Peso: ${dog.weight}", color = Color.White)
                    Text("Origen: ${dog.origin}", color = Color.White)
                    Text("Temperamento: ${dog.temperament}", color = Color.White)
                }
            }
        }
    }
}
