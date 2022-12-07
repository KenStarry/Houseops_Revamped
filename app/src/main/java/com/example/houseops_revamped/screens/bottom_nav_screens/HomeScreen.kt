package com.example.houseops_revamped.screens.bottom_nav_screens

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.custom_components.MainTopAppBar
import com.example.houseops_revamped.models.UsersCollection
import com.example.houseops_revamped.navigation.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.network.queryUserDetails
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val scope = rememberCoroutineScope()
    val db = Firebase.firestore
    val auth = Firebase.auth
    val currentUser = auth.currentUser

    var userDetails by remember {
        mutableStateOf(UsersCollection())
    }

    //  observe user data
    LaunchedEffect(key1 = currentUser!!.email) {
        withContext(Dispatchers.Main) {
            queryUserDetails(db, currentUser) { user ->
                //  update the ui accordingly
                userDetails = user
            }
        }
    }
    Scaffold(
        topBar = {
            MainTopAppBar(
                navHostController = navHostController,
                userDetails.userImageUri!!
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(it),
            horizontalAlignment = Alignment.Start
        ) {

//            SearchWidget()
        }

    }
}

@Composable
fun ColumnScope.SearchWidget() {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .background(MaterialTheme.colorScheme.onSecondary)
            .align(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  Search TextField
        SearchTextField()
    }
}


//  -------------- PREVIEWS ------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField() {

    var userInput by remember {
        mutableStateOf("")
    }

    TextField(
        value = userInput,
        onValueChange = { userInput = it },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            //  search Icon
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                //  filter Icon
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Filter Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                //  perform a search operation
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(navHostController = rememberNavController())
}