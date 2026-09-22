package com.patrimesp.cursopremiumandroid.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
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
import com.patrimesp.cursopremiumandroid.domain.entity.DogDetail
import com.patrimesp.cursopremiumandroid.ui.theme.BackgroundApp
import com.patrimesp.cursopremiumandroid.ui.theme.BackgroundComponent
import com.patrimesp.cursopremiumandroid.ui.theme.ControlColor
import com.patrimesp.cursopremiumandroid.ui.theme.PrimaryButton
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
    DetailContent(uiState, onBackPressed, onRetry = { detailViewModel.loadDogById(id) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(uiState: DetailUiState, onBackPressed: () -> Unit, onRetry: () -> Unit) {
    Scaffold(
        containerColor = BackgroundApp,
        topBar = {
            TopAppBar(
                title = { Text("Conoce a ${uiState.dog?.name ?: "tu nuevo amigo"}") },
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

            uiState.error != null -> Column(
                Modifier.fillMaxSize().padding(paddingValues).padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No se pudo cargar el detalle", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(uiState.error, color = SecondaryText)
                Spacer(Modifier.height(20.dp))
                Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = PrimaryButton)) {
                    Text("Reintentar")
                }
            }

            uiState.dog != null -> {
                val dog = uiState.dog
                DogDetailBody(dog, Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
private fun DogDetailBody(dog: DogDetail, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        AsyncImage(
            model = dog.image,
            contentDescription = "Foto de ${dog.name}",
            modifier = Modifier.fillMaxWidth().height(290.dp).clip(RoundedCornerShape(22.dp)),
            contentScale = ContentScale.Crop
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(dog.name, color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text(dog.breed, color = PrimaryButton, fontSize = 17.sp, fontWeight = FontWeight.Medium)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            DetailFact("EDAD", "${dog.age} años", Modifier.weight(1f))
            DetailFact("PESO", dog.weight, Modifier.weight(1f))
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SectionTitle("Sobre ${dog.name}")
            Text(dog.description, color = Color.White, fontSize = 16.sp, lineHeight = 24.sp)
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SectionTitle("Datos adicionales")
            DetailInfo("Origen", dog.origin)
            DetailInfo("Temperamento", dog.temperament)
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun DetailFact(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundComponent)
    ) {
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(label, color = SecondaryText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(value, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun DetailInfo(label: String, value: String) {
    Row(
        Modifier.fillMaxWidth().background(BackgroundComponent, RoundedCornerShape(16.dp)).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = SecondaryText, modifier = Modifier.width(120.dp))
        Text(value, color = Color.White, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}
