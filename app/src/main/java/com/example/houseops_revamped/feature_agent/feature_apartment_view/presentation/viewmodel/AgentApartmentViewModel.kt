package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel

import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.AgentApartmentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentApartmentViewModel @Inject constructor(
    private val useCases: AgentApartmentUseCases
) {

}