package com.example.spacexreference.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spacexreference.data.LaunchData
import com.example.spacexreference.utils.LaunchUtil
import kotlinx.coroutines.Dispatchers

class MissionViewModel(
    private val launchUtil: LaunchUtil
) : ViewModel() {

    val currentMission: MutableLiveData<LaunchData> by lazy {
        MutableLiveData<LaunchData>()
    }

    fun getCrew(id: String) = liveData(Dispatchers.IO) {
        emit(launchUtil.getCrew(id))
    }
}