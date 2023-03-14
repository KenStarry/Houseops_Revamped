package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import android.net.Uri
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

sealed class AgentApartmentEvents {

    data class AddHouse(
        val apartmentName: String,
        val houseModel: HouseModel,
        val onResponse: (response: Response<*>) -> Unit
    ) : AgentApartmentEvents()

    data class GetApartmentHouses(
        val apartmentName: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : AgentApartmentEvents()

    //  pick images from gallery
    data class AddGalleryImages(val uris: List<Uri>) : AgentApartmentEvents()

    //  update selected images list
    data class UpdateGalleryImages(val uris: List<Uri>) : AgentApartmentEvents()

    //  delete image from list
    data class DeleteImageFromList(val index: Int) : AgentApartmentEvents()

    //  add feature
    data class AddFeature(val feature: ApartmentHouseFeaturesModel) : AgentApartmentEvents()

    //  delete feature from list
    data class DeleteFeature(val feature: ApartmentHouseFeaturesModel) : AgentApartmentEvents()

    object ClearSelectedFeatures : AgentApartmentEvents()

    //  Select House Category
    data class SelectHouseCategory(val category: String) : AgentApartmentEvents()

    data class SelectHousePrice(val price: String) : AgentApartmentEvents()
}























