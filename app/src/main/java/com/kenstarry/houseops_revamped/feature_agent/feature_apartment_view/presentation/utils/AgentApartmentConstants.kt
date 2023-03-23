package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ActionCard
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel

object AgentApartmentConstants {

    const val ADD_HOUSE_BOTTOM_SHEET = "add house bottom sheet"

    //  action cards
    val actionCards = listOf(
        ActionCard("Users Booked", Icons.Outlined.Timeline),
        ActionCard("Notifications", Icons.Outlined.Notifications),
        ActionCard("Statistics", Icons.Outlined.BubbleChart),
    )

    //  house categories
    val houseCategories = listOf(
        "Single",
        "Bedsitter",
        "One Bedroom",
        "Two Bedrooms",
        "Three Bedrooms",
        "Mansion",
        "Hostel",
    )

    //  house features categories
    val featureCategoriesList = listOf(
        //  0
        "General",
        //  1
        "Bedroom",
        //  2
        "Sitting Room",
        //  3
        "Kitchen",
        //  4
        "Bathroom",
        //  5
        "Other",
    )

    //  house features list
    val featureOptionsList = listOf(
        ApartmentHouseFeaturesModel(Icons.Outlined.Kitchen, "Kitchen", featureCategoriesList[3]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Room, "Tiles", featureCategoriesList[2]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Shower, "Hot Shower", featureCategoriesList[4]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Water, "Metred Water", featureCategoriesList[0]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Thunderstorm, "Token", featureCategoriesList[0]),
        ApartmentHouseFeaturesModel(Icons.Outlined.WaterDrop, "Sink", featureCategoriesList[3]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Bathtub, "BathTub", featureCategoriesList[4]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Apartment, "Corridor", featureCategoriesList[5]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Balcony, "Balcony", featureCategoriesList[5]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Balcony, "Verandah", featureCategoriesList[5]),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.Snowshoeing,
            "Wardrobe",
            featureCategoriesList[1]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.Wash,
            "Washing Machine",
            featureCategoriesList[5]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.DoorBack,
            "Metallic Door",
            featureCategoriesList[0]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.DoorSliding,
            "Wooden Door",
            featureCategoriesList[0]
        )
    )
}