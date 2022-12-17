package com.example.houseops_revamped.screens.bottom_nav_screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.houseops_revamped.R
import com.example.houseops_revamped.custom_components.SettingsTopAppBar
import com.example.houseops_revamped.models.firestore.UsersCollection
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.network.queryUserDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {

    val scope = rememberCoroutineScope()
    val db = Firebase.firestore
    val context = LocalContext.current
    val direction = Direction(navHostController)

    val auth by remember {
        mutableStateOf(Firebase.auth)
    }

    var userDetails by remember {
        mutableStateOf(UsersCollection())
    }

    LaunchedEffect(key1 = userDetails) {
        withContext(Dispatchers.Main) {

            queryUserDetails(db, auth.currentUser?.email!!) { user ->
                userDetails = user
            }
        }
    }

    Scaffold(
        topBar = {
            SettingsTopAppBar(
                scope,
                navHostController,
                auth,
                context
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  user profile section
            ProfileSection(scope, navHostController, auth, context, userDetails)

            //  become a caretaker section
            BecomeACaretaker(direction = direction, userDetails)

        }
    }
}

//  become a caretaker section
@Composable
fun ColumnScope.BecomeACaretaker(
    direction: Direction,
    userDetails: UsersCollection
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable {
                direction.navigateToCaretakerRegistration(userDetails.userHasMadeRequest)
            }
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .align(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Become a caretaker",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
        )

        IconButton(onClick = {
            direction.navigateToCaretakerRegistration(userDetails.userHasMadeRequest)

        }) {
            Icon(
                imageVector = Icons.Sharp.ChevronRight,
                contentDescription = "Right Chevron"
            )
        }
    }
}

//  user profile section
@Composable
fun ColumnScope.ProfileSection(
    scope: CoroutineScope,
    navHostController: NavHostController,
    auth: FirebaseAuth,
    context: Context,
    userDetails: UsersCollection
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
            .align(Alignment.CenterHorizontally),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(userDetails.userImageUri)
                .crossfade(true)
                .placeholder(R.drawable.lady1)
                .build(),
            contentDescription = "User Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)

        )

        Spacer(modifier = Modifier.height(8.dp))
        //  greetings text
        Text(
            text = "Hello,",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight
        )

        //  userName
        Text(
            text = userDetails.userName!!,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
        )
    }
}

//  appearance settings

@Preview(
    showSystemUi = true
)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())
}










