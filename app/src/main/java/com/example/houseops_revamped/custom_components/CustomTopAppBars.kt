package com.example.houseops_revamped.custom_components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.navigation.BottomNavScreens

//  Main Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    navHostController: NavHostController
) {

    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Booked,
        BottomNavScreens.Wishlist,
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
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        TopAppBar(
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
            Text(text = title)
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

//  --------------- Previews ---------------- //
@Preview
@Composable
fun MainTopAppBarPrev() {
    MainTopAppBar(rememberNavController())
}



































