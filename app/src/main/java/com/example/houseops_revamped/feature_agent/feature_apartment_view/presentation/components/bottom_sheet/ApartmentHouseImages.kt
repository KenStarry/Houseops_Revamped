package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.ImageSearch
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
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHouseImages(
    primaryColor: Color,
    tertiaryColor: Color
) {

    val agentApartmentVM: AgentApartmentViewModel = hiltViewModel()
    val imagesState = agentApartmentVM.selectedImagesState

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) {
            agentApartmentVM.onEvent(AgentApartmentEvents.UpdateGalleryImages(it))
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  images title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .weight(2f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                        .background(tertiaryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = "Images icon",
                        tint = primaryColor
                    )
                }

                Text(
                    text = "Images",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

            //  add image button
            HomePillBtns(
                icon = Icons.Outlined.ImageSearch,
                title = "Add Images",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onClick = {
                    launcher.launch("image/*")
                }
            )

        }

        //  images list
        AnimatedVisibility(visible = imagesState.listOfSelectedImages.isNotEmpty()) {
            LazyRow(
                content = {
                    itemsIndexed(
                        items = imagesState.listOfSelectedImages
                    ) { index, uri ->
                        //  display the images in an image container
                        ImageItem(
                            imageUri = uri,
                            onDelete = {
                                agentApartmentVM.onEvent(
                                    AgentApartmentEvents.DeleteImageFromList(index)
                                )
                            },
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor
                        )
                    }
                },
                state = rememberLazyListState(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }
    }
}