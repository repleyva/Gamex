package com.repleyva.gamexapp.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.repleyva.gamexapp.ui.flow.NavGraphs
import com.repleyva.gamexapp.ui.flow.appCurrentDestinationAsState
import com.repleyva.gamexapp.ui.flow.startAppDestination
import com.repleyva.gamexapp.ui.theme.Neutral50
import com.repleyva.gamexapp.ui.theme.Primary50

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomBarDestination>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomBarDestination) -> Unit
) {

    val currentDestination =
        navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination

    NavigationBar(modifier = modifier) {
        items.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = currentDestination == bottomNavItem.direction,
                onClick = {
                    if (bottomNavItem.direction != currentDestination) {
                        onItemClick(bottomNavItem)
                    }
                },
                label = {
                    Text(
                        text = stringResource(bottomNavItem.label),
                        color = if (currentDestination == bottomNavItem.direction) Primary50 else Neutral50
                    )
                },
                icon = {
                    Image(
                        painter = painterResource(id = bottomNavItem.icon),
                        contentDescription = stringResource(bottomNavItem.label),
                        colorFilter = ColorFilter.tint(if (currentDestination == bottomNavItem.direction) Primary50 else Neutral50),
                        modifier = Modifier.size(26.dp)
                    )
                }
            )
        }
    }

}