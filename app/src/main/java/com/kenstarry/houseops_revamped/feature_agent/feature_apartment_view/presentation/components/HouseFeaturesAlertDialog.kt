package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FeaturedPlayList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel

@Composable
fun HouseFeaturesAlertDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: (List<ApartmentHouseFeaturesModel>) -> Unit,
    onDismiss: () -> Unit
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val featureCategoriesListState = rememberLazyListState()

    CustomAlertDialog(
        icon = Icons.Outlined.FeaturedPlayList,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "House Features",
        content = {

            //    lazy column for the feature categories
            LazyColumn(
                content = {
                    items(AgentApartmentConstants.featureCategoriesList) {

                        //  feature category list
                    }
                },
                state = featureCategoriesListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )

        },
        onConfirm = {
            onConfirm(agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures)
        },
        onDismiss = onDismiss
    )
}