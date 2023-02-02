package com.example.houseops_revamped.feature_home.home_screen.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.*
import com.example.houseops_revamped.feature_home.home_screen.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val homeVM: HomeViewModel = hiltViewModel()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
            }
        },
        topBar = {
            userDetails?.userImageUri?.let {
                HomeAppBar(
                    navHostController = navHostController,
                    userImageUrl = it
                )
            }
        },
        floatingActionButton = {
            //  only display the fab if the user is a caretaker
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "Dashboard")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Dashboard,
                        contentDescription = "Dashboard"
                    )
                },
                onClick = { /*TODO*/ },
                expanded = true,
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //  greetings text
                HomeGreetings(
                    userName = userDetails?.userName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp)
                )

                //  caretaker section
                CaretakerSection(
                    coreVM = coreVM,
                    context = context
                )

                //  pill buttons
                PillSection(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(150.dp)
                )

                //  featured section
                FeaturedSection(
                    title = "Featured",
                    context = context,
                    houses = homeVM.houses,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                    user = userDetails,
                    navHostController = navHostController,
                    snackbarHostState = snackbarHostState
                )

                ApartmentsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    title = "Apartments"
                )

                Spacer(modifier = Modifier.height(48.dp))

            }

        }

    }
}