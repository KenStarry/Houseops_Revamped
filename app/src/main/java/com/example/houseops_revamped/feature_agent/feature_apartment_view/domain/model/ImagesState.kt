package com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import android.net.Uri

data class ImagesState(
    val listOfSelectedImages: List<Uri> = emptyList()
)
