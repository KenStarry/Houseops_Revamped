package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@Composable
fun ApartmentFeaturesSection(
    lndAddApartmentVM: LndAddApartmentViewModel,
    onHouseFeaturesClicked: () -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        //  house categories
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Apartment Features",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Icon(
                imageVector = Icons.Outlined.ArrowRight,
                contentDescription = "Right arrow",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Button(
                onClick = { onHouseFeaturesClicked() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = "Add Feature",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        //  categories added
        AnimatedVisibility(visible = lndAddApartmentVM.apartmentFeatures.isNotEmpty()) {

            Spacer(modifier = Modifier.height(24.dp))

            LazyRow(
                content = {
                    itemsIndexed(
                        items = lndAddApartmentVM.apartmentFeatures
                    ) { index, feature ->
                        //  feature item
                        FeatureItem(
                            apartmentFeature = feature,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onDelete = {
                                //  delete item from list
                                lndAddApartmentVM.apartmentFeatures.removeAt(index)
                            }
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }

    }
}