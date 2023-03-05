package com.example.houseops_revamped.feature_admin.feature_agents.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_admin.feature_agents.domain.model.AdminAgentsEvents
import com.example.houseops_revamped.feature_admin.feature_agents.domain.use_case.AdminAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminAgentsViewModel @Inject constructor(
    private val useCase: AdminAgentsUseCase
) : ViewModel(){

    private val _agents = mutableStateOf<List<UsersCollection>>(emptyList())
    val agents: State<List<UsersCollection>> = _agents

    fun onEvent(event: AdminAgentsEvents) {
        when (event) {

            is AdminAgentsEvents.GetAgents -> {
                viewModelScope.launch {
                    useCase.getAgents(
                        agents = {
                            _agents.value = it
                        },
                        response = event.response
                    )
                }
            }
        }
    }
}