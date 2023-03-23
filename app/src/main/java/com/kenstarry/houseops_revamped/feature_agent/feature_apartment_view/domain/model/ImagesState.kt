package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import android.net.Uri
import com.kenstarry.houseops_revamped.core.domain.model.ImageModel

data class ImagesState(
    val listOfSelectedImages: List<ImageModel> = emptyList()
)
