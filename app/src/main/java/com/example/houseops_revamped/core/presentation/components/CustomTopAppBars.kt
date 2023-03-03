package com.example.houseops_revamped.custom_components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.houseops_revamped.R
import com.example.houseops_revamped.navigation.screens.BottomNavScreens


//  Booked Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedTopAppBar(
    navHostController: NavHostController
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
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(8.dp),
            title = {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Booked",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                )
            },
            navigationIcon = {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.lady1),
                    contentDescription = "User Profile Picture",
                    contentScale = ContentScale.Crop
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

//  Wishlist Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistTopAppBar(
    navHostController: NavHostController
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
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(8.dp),
            title = {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Wishlist",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                )
            },
            navigationIcon = {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.lady1),
                    contentDescription = "User Profile Picture",
                    contentScale = ContentScale.Crop
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

//  back press top bars
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackPressTopAppBar(
    title: String,
    onBackPressed: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Back Arrow"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )

}



































