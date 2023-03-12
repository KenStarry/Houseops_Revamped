package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HouseFeaturesCategoryItem(
    category: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val staggeredGridState = rememberLazyStaggeredGridState()
    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val filteredFeaturesList =
        AgentApartmentConstants.featureOptionsList.filter { it.featureCategory == category }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = category,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  selected items count
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.size.toString(),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )

                Text(
                    text = "/",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )

                Text(
                    text = filteredFeaturesList.size.toString(),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )
            }
        }

        //  lazy column grid for all items in the same category
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Adaptive(100.dp),
            content = {
                itemsIndexed(filteredFeaturesList) { index, apartmentHouseFeaturesModel ->

                    Spacer(modifier = Modifier.width(16.dp))
                    //    feature card
                    HouseFeatureCardItem(
                        apartmentHouseFeaturesModel = apartmentHouseFeaturesModel,
                        primaryColor = primaryColor,
                        isSelected = agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.any {
                            it == apartmentHouseFeaturesModel
                        },
                        onClick = {

                            //  check if this feature is already selected
                            if (agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.any {
                                    it == apartmentHouseFeaturesModel
                                }) {
                                //  remove the item to selected features
                                agentApartmentVM.onEvent(
                                    AgentApartmentEvents.DeleteFeature(
                                        apartmentHouseFeaturesModel
                                    )
                                )
                            } else {
                                //  add the item to selected features
                                agentApartmentVM.onEvent(
                                    AgentApartmentEvents.AddFeature(
                                        apartmentHouseFeaturesModel
                                    )
                                )
                            }
                        }
                    )
                }
            },
            state = staggeredGridState,
            modifier = Modifier
                .wrapContentWidth()
                .height(100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )

    }

}
































