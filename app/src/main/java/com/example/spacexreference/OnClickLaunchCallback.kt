package com.example.spacexreference

import android.view.View
import com.example.spacexreference.data.LaunchData

interface OnClickLaunchCallback {

    fun onClick(launchData: LaunchData, view: View)
}