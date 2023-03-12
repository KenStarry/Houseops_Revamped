package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel

object AgentApartmentConstants {

    const val ADD_HOUSE_BOTTOM_SHEET = "add house bottom sheet"

    //  house features categories
    val featureCategoriesList = listOf(
        "Essentials",
        "Interior",
        "Leisure",
        "Others",
    )

    //  house features list
    val featureOptionsList = listOf(
        ApartmentHouseFeaturesModel(Icons.Outlined.Kitchen, "Kitchen", featureCategoriesList[1]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Room, "Tiles", featureCategoriesList[1]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Shower, "Hot Shower", featureCategoriesList[1]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Water, "Metred Water", featureCategoriesList[0]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Thunderstorm, "Token", featureCategoriesList[0]),
        ApartmentHouseFeaturesModel(Icons.Outlined.WaterDrop, "Sink", featureCategoriesList[2]),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.Bathroom,
            "Bathroom(s)",
            featureCategoriesList[1]
        ),
        ApartmentHouseFeaturesModel(Icons.Outlined.Bathtub, "BathTub", featureCategoriesList[2]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Apartment, "Corridor", featureCategoriesList[3]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Balcony, "Balcony", featureCategoriesList[3]),
        ApartmentHouseFeaturesModel(Icons.Outlined.Balcony, "Verandah", featureCategoriesList[3]),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.Snowshoeing,
            "Wardrobe",
            featureCategoriesList[1]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.Wash,
            "Washing Machine",
            featureCategoriesList[2]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.DoorBack,
            "Metallic Door",
            featureCategoriesList[1]
        ),
        ApartmentHouseFeaturesModel(
            Icons.Outlined.DoorSliding,
            "Wooden Door",
            featureCategoriesList[1]
        )
    )
}