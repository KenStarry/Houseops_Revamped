package com.example.houseops_revamped.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.custom_components.MainTopAppBar
import com.example.houseops_revamped.navigation.BottomNavScreens
import com.example.houseops_revamped.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    Scaffold(
        topBar = { MainTopAppBar(navHostController = navHostController) },
        bottomBar = { MainBottomBar(navHostController = navHostController) },
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            RootNavGraph(navHostController = navHostController)
        }

    }
}

//  navigation bar
@Composable
fun MainBottomBar(
    navHostController: NavHostController
) {

    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Booked,
        BottomNavScreens.Wishlist,
        BottomNavScreens.Settings,
    )

    //  current destination
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //  check if any of the screens has a route that's not at home
    val bottombarDestination = screens.any { it.route == currentDestination?.route }
    val bottombarVisibilityState = remember {
        mutableStateOf(true)
    }

    bottombarVisibilityState.value = bottombarDestination

    //  animate the visibility
    AnimatedVisibility(
        visible = bottombarVisibilityState.value,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {
            screens.forEach { screen ->
                MainBottomBarItem(
                    navHostController = navHostController,
                    currentDestination = currentDestination,
                    screen = screen
                )
            }
        }
    }
}

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
                popUpTo(navHostController.graph.findStartDestination().id)
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
                                .background(Color.Red)
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
        }
    )

}


//  --------------- Previews ----------------- //

@Preview
@Composable
fun MainBottomBarPrev() {
    MainBottomBar(navHostController = rememberNavController())
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navHostController = rememberNavController())
}





































