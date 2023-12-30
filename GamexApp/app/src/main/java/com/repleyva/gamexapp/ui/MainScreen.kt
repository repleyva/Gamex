package com.repleyva.gamexapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.repleyva.gamexapp.ui.flow.NavGraphs
import com.repleyva.gamexapp.ui.nav.BottomBarDestination
import com.repleyva.gamexapp.ui.nav.BottomNavigationBar

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val shouldShowBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in BottomBarDestination.entries
        .map { it.direction.route }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                shouldShowBottomBar = shouldShowBottomBar,
                navController = navController
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                navController = navController
            )
        }
    }
}

@Composable
private fun BottomNavigation(
    shouldShowBottomBar: Boolean,
    navController: NavHostController,
) {
    if (shouldShowBottomBar) {
        BottomNavigationBar(
            navController = navController,
            items = BottomBarDestination.entries,
            onItemClick = {
                navController.navigate(it.direction) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}