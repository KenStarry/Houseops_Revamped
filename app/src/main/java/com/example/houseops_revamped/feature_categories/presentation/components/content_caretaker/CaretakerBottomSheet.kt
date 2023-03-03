package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.core.presentation.components.Lottie
import com.example.houseops_revamped.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HouseItem
import com.example.houseops_revamped.navigation.Direction

@Composable
fun CaretakerBottomSheet(
    categoriesVM: CategoriesViewModel,
    caretaker: Caretaker?,
    userDetails: UsersCollection?,
    direction: Direction,
    onHouseClicked: (house: HouseModel) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val caretakerHouses =
        categoriesVM.getCaretakerHouses(caretaker?.caretakerApartment ?: "none")

    //  Main content
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        //  caretaker details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.onPrimary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                CoilImage(
                    context = context,
                    imageUri = caretaker?.caretakerImage?.toUri(),
                    placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(70.dp)
                )
            }

            //  content
            Column(
                modifier = Modifier
                    .weight(4f)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = caretaker?.caretakerName ?: "Anonymous",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    HomePillBtns(
                        icon = Icons.Outlined.Apartment,
                        title = caretaker?.caretakerApartment ?: "none",
                        onClick = {},
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )

                    //  caretaker apartments count
                    Text(
                        text = "${caretakerHouses.size} ${
                            categoriesVM.addSuffixSToWord(
                                caretakerHouses.size,
                                "house"
                            )
                        }",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        //  caretaker houses
        Text(
            text = "Houses",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (caretakerHouses.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Lottie(
                    rawFile = R.raw.search_empty,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(100.dp),
                    iterations = 1,
                    isPlaying = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "No Houses yet...",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }
        } else {
            LazyRow(
                content = {

                    items(
                        items = caretakerHouses
                    ) { house ->

                        userDetails?.let {
                            HouseItem(
                                context = context,
                                house = house,
                                user = it,
                                snackbarHostState = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .size(
                                        width = 190.dp,
                                        height = 260.dp
                                    )
                                    .background(MaterialTheme.colorScheme.onSecondary)
                                    .clickable {
                                        onHouseClicked(house)
                                    }
                                    .padding(8.dp),
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )

                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                },
                state = listState
            )
        }
    }
}



















