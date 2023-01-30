package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun DetailsCaretaker(
    context: Context,
    apartment: String,
    coreVM: CoreViewModel = hiltViewModel()
) {

    val caretaker = coreVM.getCaretakerDetails(apartment)

    caretaker?.let {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  caretaker icon
            CoilImage(
                context = context,
                imageUri = it.caretakerImage?.toUri(),
                placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )

        }

    }
}