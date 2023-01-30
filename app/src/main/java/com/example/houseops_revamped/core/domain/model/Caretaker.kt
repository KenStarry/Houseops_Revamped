package com.example.houseops_revamped.core.domain.model

import android.net.Uri

data class Caretaker(
    val caretakerName: String?,
    val caretakerImage: String?,
    val caretakerApartment: String?,
    val caretakerId: String?,
    val caretakerEmail: String?,
    val caretakerPassword: String?
) {
    constructor() : this ("", null, "", "", "", "")
}
