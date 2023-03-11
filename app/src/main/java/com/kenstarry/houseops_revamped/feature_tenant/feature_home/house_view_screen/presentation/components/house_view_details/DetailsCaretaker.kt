package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.Caretaker
import com.kenstarry.houseops_revamped.core.presentation.components.CoilImage
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.ui.theme.LimeGreen
import com.kenstarry.houseops_revamped.ui.theme.LimeGreenDull

@Composable
fun DetailsCaretaker(
    context: Context,
    apartment: String,
    coreVM: CoreViewModel = hiltViewModel(),
    onCardClicked: (caretaker: Caretaker) -> Unit,
    onPhoneClicked: (phone: String) -> Unit
) {

    val caretaker = coreVM.getCaretakerDetails(apartment)

    caretaker?.let {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    onCardClicked(it)
                },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  caretaker icon
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    CoilImage(
                        context = context,
                        imageUri = it.caretakerImage?.toUri(),
                        placeholder = R.drawable.houseops_dark_final,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                    )
                }

                //  caretaker name and role
                Column(
                    modifier = Modifier
                        .weight(3f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    //  name
                    Text(
                        text = it.caretakerName ?: "",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    //  role
                    Text(
                        text = "caretaker",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                }

                //  phone call
                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {
                            //    add caretaker
                            onPhoneClicked("0717446607")
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = LimeGreen,
                            containerColor = LimeGreenDull
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Phone call"
                        )
                    }

                }
            }
        }

    }
}


















