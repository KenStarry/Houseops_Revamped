package com.example.houseops_revamped.feature_main_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.houseops_revamped.navigation.BottomNavScreens

//  navigation bar item
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.MainBottomBarItem(
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    screen: BottomNavScreens
) {

    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            navHostController.navigate(route = screen.route) {
                popUpTo(BottomNavScreens.Home.route)
                launchSingleTop = true
            }
        },

        alwaysShowLabel = false,

        label = {
            Text(text = screen.title)
        },

        icon = {
            if (screen.badgeCount > 0) {
                BadgedBox(
                    badge = {
                        Text(
                            text = screen.badgeCount.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.tertiary)
                                .wrapContentSize()
                                .padding(horizontal = 4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                ) {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Bottom Nav Icon"
                    )
                }
            } else {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "Bottom Nav Icon"
                )
            }
        },

        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            indicatorColor = MaterialTheme.colorScheme.tertiary
        ),

    )

}


















