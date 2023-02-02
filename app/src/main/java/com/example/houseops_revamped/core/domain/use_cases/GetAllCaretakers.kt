package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.repository.CoreRepository

class GetAllCaretakers(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(caretakers: (List<Caretaker>?) -> Unit) =
        repository.getAllCaretakers { caretakers(it) }
}