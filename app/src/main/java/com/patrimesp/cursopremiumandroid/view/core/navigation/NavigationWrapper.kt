package com.patrimesp.cursopremiumandroid.view.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.patrimesp.cursopremiumandroid.view.detail.DetailScreen
import com.patrimesp.cursopremiumandroid.view.main.MainScreen

@Composable
fun NavigationWrapper() {
    val backstack = rememberNavBackStack(Main)
    NavDisplay(backStack = backstack, entryProvider = entryProvider {
        entry<Main> {
            Text("Main")
        }

        entry<Detail> {
            Text("")
        }
    })


}