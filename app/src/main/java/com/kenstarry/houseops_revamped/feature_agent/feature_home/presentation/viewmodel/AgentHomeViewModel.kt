package com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.model.AgentHomeEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.use_cases.AgentHomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentHomeViewModel @Inject constructor(
    private val useCases: AgentHomeUseCases
) : ViewModel() {

    private val _agentApartments = mutableStateOf<List<Apartment>>(emptyList())
    val agentApartments: State<List<Apartment>> = _agentApartments

    fun onEvent(event: AgentHomeEvents) {

        when (event) {

            is AgentHomeEvents.GetAgentApartments -> {
                viewModelScope.launch {
                    useCases.getAgentApartments(
                        email = event.email,
                        apartments = { apartmentsList ->
                            _agentApartments.value = apartmentsList
                        },
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}