package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.repository.CoreRepository

class CaretakerDetails(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        caretaker: (caretaker: Caretaker?) -> Unit
    ) = repository.getCaretakerDetails(
        apartmentName = apartmentName,
        caretaker = { caretaker(it) }
    )
}