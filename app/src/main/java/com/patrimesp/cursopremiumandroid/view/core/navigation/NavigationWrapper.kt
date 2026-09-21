package com.patrimesp.cursopremiumandroid.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patrimesp.cursopremiumandroid.view.detail.DetailScreen
import com.patrimesp.cursopremiumandroid.view.main.MainScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Main) {
        composable<Main> {
            MainScreen(navigateToRegister = { navController.navigate(Detail)})
        }

        composable<Detail> {
            DetailScreen(navigateBack = { navController.popBackStack()})
        }
    }


}