package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.kenstarry.houseops_revamped.core.domain.model.PlacesAPIResult
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlacesBottomSheet(
    lndAddApartmentVM: LndAddApartmentViewModel,
    onInput: (input: PlacesAPIResult) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var textLocation by remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        CustomTextField(
            textFieldValue = textLocation,
            startIcon = Icons.Outlined.Search,
            endIcon = null,
            placeholder = "Search",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = {
                textLocation = it
                lndAddApartmentVM.onEvent(LndApartmentEvents.SearchPlaces(it))
            }
        )

        AnimatedVisibility(visible = lndAddApartmentVM.locationAutoFill.isNotEmpty()) {
            LazyColumn(
                content = {
                    items(
                        items = lndAddApartmentVM.locationAutoFill
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {

                                    keyboardController?.hide()
                                    textLocation = it.address
                                    lndAddApartmentVM.locationAutoFill.clear()
                                    onInput(it)
                                }
                        ) {

                            Text(
                                text = it.address,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                        }
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        AnimatedVisibility(visible = lndAddApartmentVM.locationAutoFill.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Lottie(
                    rawFile = R.raw.search_icon,
                    isPlaying = true,
                    iterations = Int.MAX_VALUE,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (textLocation.isBlank())
                        "Where is your apartment located?"
                    else
                        "Oops, could not find any results",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }
        }

    }
}