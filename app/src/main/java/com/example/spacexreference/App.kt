package com.example.spacexreference

import android.app.Application
import com.example.spacexreference.dagger.AppComponent
import com.example.spacexreference.dagger.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder().build()
}