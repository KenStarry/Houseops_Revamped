package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterNone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.house_view_screen.domain.utils.HouseViewConstants
import com.example.houseops_revamped.feature_home.house_view_screen.domain.utils.HouseViewConstants.featuresList

@Composable
fun DetailsFeatures(
    features: List<String>
) {

    LazyRow(
        content = {
            items(
                items = features
            ) {

                val icon = when (it) {
                    featuresList[0].featureName -> featuresList[0].featureIcon
                    featuresList[1].featureName -> featuresList[1].featureIcon
                    featuresList[2].featureName -> featuresList[2].featureIcon
                    featuresList[3].featureName -> featuresList[3].featureIcon
                    featuresList[4].featureName -> featuresList[4].featureIcon
                    featuresList[5].featureName -> featuresList[5].featureIcon
                    else -> null
                }

                FeatureItem(
                    featureIcon = icon,
                    featureName = it
                )
            }
        },
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    )

}