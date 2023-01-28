package com.example.houseops_revamped.feature_home.home_screen.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.houseops_revamped.navigation.BottomNavScreens

//  Main Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    navHostController: NavHostController,
    userImageUrl: String
) {

    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Booked,
        BottomNavScreens.Bookmarks,
        BottomNavScreens.Settings,
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val targetDestination = screens.any { it.route == currentDestination?.route }
    val topAppBarVisibilityState = remember {
        mutableStateOf(true)
    }

    topAppBarVisibilityState.value = targetDestination

    //  show or hide visibility as per state
    AnimatedVisibility(
        visible = topAppBarVisibilityState.value,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            title = {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Home",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                )
            },
            navigationIcon = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .clickable {
                            //  navigate to settings screen
                            navHostController.navigate(BottomNavScreens.Settings.route)
                        },
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Sharp.Notifications,
                        contentDescription = "Notification Icon"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search Icon"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Sharp.MoreVert,
                        contentDescription = "More Vertical Icon"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}