package com.example.houseops_revamped.feature_home.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.models.ExploreLocationsModel
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_home.presentation.components.HomeApartments
import com.example.houseops_revamped.feature_home.presentation.components.HomeAppBar
import com.example.houseops_revamped.feature_home.presentation.components.HomeGreetings
import com.example.houseops_revamped.feature_home.presentation.components.HomePillBtns
import com.example.houseops_revamped.feature_home.presentation.models.PillBtn
import com.example.houseops_revamped.feature_home.presentation.utils.HomeConstants
import com.example.houseops_revamped.ui.theme.BlueAccentLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    Scaffold(
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
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //  greetings text
                HomeGreetings(
                    userName = userDetails?.userName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 16.dp)
                )

                //  pill buttons
                LazyHorizontalGrid(
                    rows = GridCells.Adaptive(50.dp),
                    content = {
                        itemsIndexed(
                            HomeConstants.homePills
                        ) { index, pill ->

                            HomePillBtns(
                                icon = pill.icon,
                                title = pill.title,
                                onClick = {
                                    //  pass the category at the specific index
                                    Log.d("category", HomeConstants.homePills[index].title)
                                }
                            )

                        }
                    },
                    state = rememberLazyGridState(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                )

                HomeApartments(title = "Apartments")


            }

        }

    }
}

@Composable
fun ExploreLocationsItem(
    image: Painter = painterResource(id = R.drawable.house1),
    title: String = "Mombasa",
    desc: String = "500km"
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(height = 70.dp, width = 220.dp)
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  The image
        Image(
            painter = image,
            contentDescription = "Explore image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(60.dp)
        )

        //  description
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            //  title
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  distance icon
                Icon(
                    imageVector = Icons.Sharp.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    modifier = Modifier
                        .size(16.dp)
                )

                //  desc
                Text(
                    text = desc,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

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
fun ExploreLocationsItemPrev() {
    ExploreLocationsItem()
}

@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(navHostController = rememberNavController())
}