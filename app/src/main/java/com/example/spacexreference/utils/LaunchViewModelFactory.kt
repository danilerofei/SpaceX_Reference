package com.example.spacexreference.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacexreference.models.LaunchViewModel
import com.example.spacexreference.models.MissionViewModel

class LaunchViewModelFactory(
    private val launchUtil: LaunchUtil
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaunchViewModel::class.java)) {
            return LaunchViewModel(launchUtil) as T
        }
        if (modelClass.isAssignableFrom(MissionViewModel::class.java)) {
            return MissionViewModel(launchUtil) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}