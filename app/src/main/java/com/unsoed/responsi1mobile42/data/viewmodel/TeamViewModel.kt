package com.unsoed.responsi1mobile42.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.responsi1mobile42.data.model.TeamResponse
import com.unsoed.responsi1mobile42.data.network.RetrofitInstance
import com.unsoed.responsi1mobile42.data.utils.Constants
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    private val _teamData = MutableLiveData<TeamResponse>()
    val teamData: LiveData<TeamResponse> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchTeamData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeam(
                    Constants.API_TOKEN,
                    Constants.MAN_CITY_TEAM_ID
                )
                if (response.isSuccessful) {
                    _teamData.value = response.body()
                } else {
                    _error.value = "Failed to fetch data: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}