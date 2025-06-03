package com.rff.boingballdemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

sealed interface Graph

@Serializable
data object MainGraph : Graph {
    @Serializable
    data object Home : Graph

    @Serializable
    data object Preferences : Graph
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = MainGraph
    ) {
        mainGraph(navController)
    }
}

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation<MainGraph>(
        startDestination = MainGraph.Home
    ) {
        composable<MainGraph.Home> {
            BoingBallScreen(
                onPreferencesClick = {
                    navController.navigate(MainGraph.Preferences)
                }
            )
        }
        composable<MainGraph.Preferences> {
            PreferencesScreen()
        }
    }
}
