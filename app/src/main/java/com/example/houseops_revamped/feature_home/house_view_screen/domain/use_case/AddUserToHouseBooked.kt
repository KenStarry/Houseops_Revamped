package com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case

import android.util.Log
import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository

class AddUserToHouseBooked(
    private val repo: HouseViewRepository
) {
    suspend operator fun invoke(
        apartmentName: String,
        houseCategory: String,
        userEmail: String,
        isAdd: Boolean
    ) {
        repo.addUserToHouseBooked(
            apartmentName, houseCategory, userEmail, isAdd
        )

        Log.d("booked", "use case -> $apartmentName, $houseCategory")
    }
}