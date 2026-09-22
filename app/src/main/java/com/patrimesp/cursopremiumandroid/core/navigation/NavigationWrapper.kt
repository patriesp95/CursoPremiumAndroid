package com.patrimesp.cursopremiumandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.patrimesp.cursopremiumandroid.presentation.main.MainScreen
import com.patrimesp.cursopremiumandroid.presentation.detail.DetailScreen

@Composable
fun NavigationWrapper() {
    val backstack = rememberNavBackStack(Main)
    NavDisplay(backStack = backstack, entryProvider = entryProvider {
        entry<Main> {
            MainScreen(onItemTapped = { id ->
                    backstack.add(Detail(id))
                }
            )
        }

        entry<Detail> { params ->
            DetailScreen(id = params.id, onBackPressed = {backstack.removeLastOrNull()})
        }
    })


}