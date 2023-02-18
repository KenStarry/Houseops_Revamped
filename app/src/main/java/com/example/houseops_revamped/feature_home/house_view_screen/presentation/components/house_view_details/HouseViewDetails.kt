package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseViewDetails(
    context: Context,
    house: HouseModel,
    userDetails: UsersCollection?,
    onCaretakerClicked: (caretaker: Caretaker) -> Unit
) {

    val spacing = 24.dp

    //  action icons
    DetailActionIcons(
        house = house,
        userDetails = userDetails
    )

    Spacer(modifier = Modifier.height(spacing))

    //  title
    DetailsTitle(
        apartmentName = house.houseApartmentName,
        houseCategory = house.houseCategory
    )

    Spacer(modifier = Modifier.height(spacing))

    //  caretaker information & call / message icons
    DetailsCaretaker(
        context = context,
        apartment = house.houseApartmentName,
        onCardClicked = {
            onCaretakerClicked(it)
        },
        onPhoneClicked = { phoneNumber ->

            val parsePhone = Uri.parse("tel:$phoneNumber")
            val intent = Intent(Intent.ACTION_DIAL, parsePhone)

            //  open intent
            try {
                context.startActivity(intent)

            } catch (e: SecurityException) {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Spacer(modifier = Modifier.height(spacing))

    //  features
    DetailsFeatures(
        features = house.houseFeatures
    )

    Spacer(modifier = Modifier.height(spacing))

    //  description
    DetailsDescription(
        description = house.houseDescription
    )

}
























