package com.example.houseops_revamped.core.domain.model

import android.net.Uri

data class Landlord(
    val landlordName: String?,
    val landlordImage: String?,
    val landlordEmail: String?,
    val landlordPassword: String?,
    val isLandlordVerified: Boolean,
    val userType: String
) {
    constructor() : this ("", null, "", "", false, "")
}
