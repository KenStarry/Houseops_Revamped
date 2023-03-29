package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import android.widget.Toast
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
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.ImageSearch
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.ImageModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHouseImages(
    houseModel: HouseModel?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val agentApartmentVM: AgentApartmentViewModel = hiltViewModel()
    val imagesState = agentApartmentVM.selectedImagesState

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) {

            val imageModelList = mutableListOf<ImageModel>()

            it.forEach { uri ->
                val currentTime = System.currentTimeMillis()
                imageModelList.add(ImageModel(uri.toString(), currentTime.toString()))
            }

            agentApartmentVM.onEvent(AgentApartmentEvents.UpdateGalleryImages(imageModelList))

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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Images",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

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
                        items = houseModel?.houseImageUris
                            ?: imagesState.listOfSelectedImages
                    ) { index, imageModel ->
                        //  display the images in an image container
                        ImageItem(
                            houseModel = houseModel,
                            imageUri = imageModel.uri.toUri(),
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