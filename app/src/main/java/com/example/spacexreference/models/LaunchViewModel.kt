package com.example.spacexreference.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spacexreference.utils.LaunchUtil
import kotlinx.coroutines.Dispatchers

class LaunchViewModel(
    private val launchUtil: LaunchUtil
) : ViewModel() {

    fun getLaunches() = liveData(Dispatchers.IO) {
        emit(launchUtil.getLaunches())
    }
}
