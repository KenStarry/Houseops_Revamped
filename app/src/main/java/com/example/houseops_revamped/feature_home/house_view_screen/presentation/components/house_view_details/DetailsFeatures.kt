package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.runtime.Composable
import com.example.houseops_revamped.feature_home.house_view_screen.domain.utils.HouseViewConstants

@Composable
fun DetailsFeatures(
    features: List<String>
) {

    HouseViewConstants.featuresList.forEach { houseFeatures ->

        features.forEach { feature ->

            FeatureItem()
        }
    }

}