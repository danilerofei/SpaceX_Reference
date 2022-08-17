package com.example.spacexreference.dagger

import com.example.spacexreference.activities.ActivityLaunchInfo
import com.example.spacexreference.activities.ActivityMain
import com.example.spacexreference.retrofit.RetrofitService
import com.example.spacexreference.utils.LaunchUtil
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LaunchUtil::class, RetrofitInject::class])
interface AppComponent {

    fun retrofitService(): RetrofitService

    fun inject(activity: ActivityMain)

    fun inject(activity: ActivityLaunchInfo)
}