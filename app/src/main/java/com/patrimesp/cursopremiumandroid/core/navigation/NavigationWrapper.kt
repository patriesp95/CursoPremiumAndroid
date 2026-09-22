package com.patrimesp.cursopremiumandroid.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.patrimesp.cursopremiumandroid.presentation.main.MainScreen

@Composable
fun NavigationWrapper() {
    val backstack = rememberNavBackStack(Main)
    NavDisplay(backStack = backstack, entryProvider = entryProvider {
        entry<Main> {
            MainScreen(onBackSelected = {backstack.removeLastOrNull()})
        }

        entry<Detail> {
            Text("")
        }
    })


}