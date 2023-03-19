package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.viewmodel.AdminHomeViewModel

@Composable
fun AdminViewLandlords(
    adminHomeVM: AdminHomeViewModel = hiltViewModel(),
    currentUser: FirebaseUser?,
    context: Context,
    primaryColor: Color,
    tertiaryColor: Color,
    onActionsClicked: (landlord: UsersCollection) -> Unit,
    onCardClicked: (landlord: UsersCollection) -> Unit,
) {

    val state = rememberLazyListState()

    LazyColumn(
        content = {
            items(adminHomeVM.landlords.value) { landlord ->

                //  landlord item
                AdminLandlordItem(
                    landlord = landlord,
                    currentUser = currentUser,
                    context = context,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onCardClicked = { onCardClicked(landlord) },
                    onActionsClicked = { onActionsClicked(landlord) }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        state = state,
        modifier = Modifier
            .fillMaxSize()
    )

}