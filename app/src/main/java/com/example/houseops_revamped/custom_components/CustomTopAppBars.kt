package com.example.houseops_revamped.custom_components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.sharp.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.houseops_revamped.R
import com.example.houseops_revamped.utilities.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.utilities.Constants.HOME_ROUTE
import com.example.houseops_revamped.models.TopbarDropdown
import com.example.houseops_revamped.navigation.BottomNavScreens
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.network.logoutUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//  Main Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    navHostController: NavHostController,
    userImageUrl: String
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

//  Booked Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedTopAppBar(
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

//  Settings Top App Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar(
    scope: CoroutineScope,
    navHostController: NavHostController,
    auth: FirebaseAuth,
    context: Context
) {

    val direction = Direction(navHostController)

    //  toggle menu on and off boolean
    var showMenu by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(8.dp),
        title = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Settings",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.navigate(BottomNavScreens.Home.route) {
                    popUpTo(BottomNavScreens.Home.route)
                    launchSingleTop = true
                }
            }) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Back Arrow"
                )
            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Sharp.MoreVert,
                    contentDescription = "More Vertical Icon"
                )
            }

            //  dropdown menu
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                properties = PopupProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    clippingEnabled = true
                )
            ) {

                //  list of drop down items
                val itemsList = if (auth.currentUser?.email == "starrycodes@gmail.com") {
                    //  Admin
                    listOf(
                        TopbarDropdown("Admin Page", Icons.Sharp.AdminPanelSettings) {
                            //  navigate to admin page
                            direction.navigateToAdminPage()
                        },
                        TopbarDropdown("Logout", Icons.Sharp.Logout) {
                            scope.launch(Dispatchers.Main) {
                                logoutUser(auth, context, navHostController) {
                                    auth.signOut()
                                    //  navigate back to login screen
                                    navHostController.navigate(AUTHENTICATION_ROUTE) {
                                        popUpTo(HOME_ROUTE)
                                    }
                                }
                            }
                        },
                        TopbarDropdown("Delete Account", Icons.Sharp.DeleteForever) {
                            Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT).show()
                        }
                    )
                } else {
                    //  Normal User
                    listOf(
                        TopbarDropdown("Logout", Icons.Sharp.Logout) {
                            scope.launch(Dispatchers.Main) {
                                logoutUser(auth, context, navHostController) {
                                    auth.signOut()
                                    //  navigate back to login screen
                                    navHostController.navigate(AUTHENTICATION_ROUTE) {
                                        popUpTo(HOME_ROUTE)
                                    }
                                }
                            }
                        },
                        TopbarDropdown("Delete Account", Icons.Sharp.DeleteForever) {
                            Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                CustomDropdownMenuItem(
                    dropdownItems = itemsList
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
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

//  --------------- Previews ---------------- //
@Preview
@Composable
fun MainTopAppBarPrev() {
    MainTopAppBar(rememberNavController(), "")
}



































