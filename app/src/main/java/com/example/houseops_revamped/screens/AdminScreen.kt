package com.example.houseops_revamped.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.network.queryRegisteredCaretakers
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun AdminScreen(
    navHostController: NavHostController
) {

    val context = LocalContext.current

    val auth by remember {
        mutableStateOf(Firebase.auth)
    }
    val db by remember {
        mutableStateOf(Firebase.firestore)
    }
    val pagerState = rememberPagerState()
    val allRegisteredCaretakers = remember {
        mutableStateListOf<UsersCollection>()
    }

    //  query all registred caretakers
    LaunchedEffect(key1 = true) {
        queryRegisteredCaretakers(db) { usersList ->
            allRegisteredCaretakers.addAll(usersList)
        }
    }

    Scaffold(
        topBar = {
            BackPressTopAppBar(title = "Admin") {
                Direction(navHostController).navigateBack()
            }
        }
    ) { contentPadding ->

        //  admin page
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  title
                Text(
                    text = "Requests",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  number of pending requests
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(MaterialTheme.colorScheme.onSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = allRegisteredCaretakers.size.toString(),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //  pager indicator dots
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = MaterialTheme.colorScheme.primary,
                        inactiveColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.3f),
                        indicatorHeight = 5.dp,
                        indicatorWidth = 5.dp,
                        indicatorShape = CircleShape,
                        spacing = 4.dp
                    )

                }

                //  horizontal pager
                HorizontalPager(
                    count = allRegisteredCaretakers.size,
                    state = pagerState,
                    contentPadding = PaddingValues(32.dp),
                    itemSpacing = 16.dp
                ) {
                    //  pager page
                    RegisteredCaretakerPage(
                        context = context,
                        userDetails = allRegisteredCaretakers[it]
                    )
                }

            }

        }

    }
}

@Composable
fun RegisteredCaretakerPage(
    context: Context,
    userDetails: UsersCollection
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //  caretaker id card
//        CaretakerIDCard(context = context, userDetails = userDetails)

        Spacer(modifier = Modifier.height(16.dp))

        //  caretaker approve / reject buttons
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            //  approve button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Approve")
            }

            Spacer(modifier = Modifier.width(16.dp))

            //  reject button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = "Reject")
            }

        }


    }
}










