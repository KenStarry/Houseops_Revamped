package com.example.houseops_revamped.feature_home.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.CoreEvents
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_home.domain.model.HouseModel

@Composable
fun ThumbsUp(
    modifier: Modifier = Modifier,
    houseModel: HouseModel
) {

    val coreVM: CoreViewModel = hiltViewModel()

    var isThumbsUpClicked by remember {
        mutableStateOf(false)
    }

    var likesInteger by remember {
        mutableStateOf(houseModel.houseLikes.toInt())
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary)
                .clickable {

                    isThumbsUpClicked = !isThumbsUpClicked

                    if (isThumbsUpClicked)
                        likesInteger += 1
                    else
                        if (likesInteger > 0)
                            likesInteger -= 1

                    //  update firestore field
                    coreVM.onEvent(
                        CoreEvents.UpdateFirestoreField(
                            collectionName = Constants.APARTMENTS_COLLECTION,
                            documentName = houseModel.houseApartmentName,
                            subCollectionName = Constants.HOUSES_SUB_COLLECTION,
                            subCollectionDocument = houseModel.houseCategory,
                            fieldName = "houseLikes",
                            fieldValue = likesInteger.toString()
                        )
                    )
                }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = if (isThumbsUpClicked)
                    Icons.Filled.ThumbUp
                else
                    Icons.Outlined.ThumbUp,

                contentDescription = "Like Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = likesInteger.toString(),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

        }

    }
}