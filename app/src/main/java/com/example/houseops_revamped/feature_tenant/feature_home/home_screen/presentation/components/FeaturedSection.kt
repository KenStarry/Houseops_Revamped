package com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HouseItem
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.viewmodel.HomeViewModel
import com.example.houseops_revamped.navigation.Direction
import kotlinx.coroutines.launch

@Composable
fun FeaturedSection(
    modifier: Modifier = Modifier,
    homeVM: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController,
    context: Context,
    title: String,
    houses: List<HouseModel>,
    user: UsersCollection?,
    snackbarHostState: SnackbarHostState,
    primaryColor: Color,
    tertiaryColor: Color
) {

//    val homeVM: HomeViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            listState.animateScrollToItem(0)
        }
    }

    homeVM.getHouses()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
        )

        //  houses
        LazyRow(
            content = {

                itemsIndexed(
                    houses
                ) { index, house ->
                    HouseItem(
                        context = context,
                        house = house,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(
                                width = 190.dp,
                                height = 260.dp
                            )
                            .background(MaterialTheme.colorScheme.onSecondary)
                            .clickable {
                                //  open house view Screen
                                direction.navigateToHouseView(
                                    house.houseApartmentName, house.houseCategory
                                )
                            }
                            .padding(8.dp),
                        user = user,
                        snackbarHostState = snackbarHostState,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                }
            },
            state = listState,
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
        )

    }

}





















