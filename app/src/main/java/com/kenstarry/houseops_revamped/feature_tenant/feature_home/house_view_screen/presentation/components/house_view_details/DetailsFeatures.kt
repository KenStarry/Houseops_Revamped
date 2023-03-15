package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseFeatureCardItem
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.utils.HouseViewConstants.featuresList

@Composable
fun DetailsFeatures(
    features: List<String>,
    primaryColor: Color,
    tertiaryColor: Color,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "House Features",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        LazyRow(
            content = {
                items(
                    items = features
                ) { feature ->

                    val featureDetails = AgentApartmentConstants.featureOptionsList.filter { it.title == feature }[0]

                    HouseFeatureCardItem(
                        apartmentHouseFeaturesModel = featureDetails,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        isSelected = false,
                        onClick = {

                        }
                    )
                }
            },
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}