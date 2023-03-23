package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import android.net.Uri
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.ImageModel

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
    data class AddGalleryImages(val uris: List<ImageModel>) : AgentApartmentEvents()

    //  update selected images list
    data class UpdateGalleryImages(val uris: List<ImageModel>) : AgentApartmentEvents()

    //  delete image from list
    data class DeleteImageFromList(val index: Int) : AgentApartmentEvents()

    //  clear all images
    object ClearGalleryImages : AgentApartmentEvents()

    //  add feature
    data class AddFeature(val feature: ApartmentHouseFeaturesModel) : AgentApartmentEvents()

    //  delete feature from list
    data class DeleteFeature(val feature: ApartmentHouseFeaturesModel) : AgentApartmentEvents()

    object ClearSelectedFeatures : AgentApartmentEvents()

    //  Select House Category
    data class SelectHouseCategory(val category: String) : AgentApartmentEvents()

    data class SelectHousePrice(val price: String) : AgentApartmentEvents()

    data class SelectVacantUnits(val count: Int) : AgentApartmentEvents()

    data class SelectHouseDescription(val description: String) : AgentApartmentEvents()
}























