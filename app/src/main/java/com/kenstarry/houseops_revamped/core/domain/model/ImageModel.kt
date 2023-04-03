package com.kenstarry.houseops_revamped.core.domain.model

data class ImageModel(
    val uri: String,
    val time: String,
    val extension: String
) {
    constructor() : this ("", "", "")
}
