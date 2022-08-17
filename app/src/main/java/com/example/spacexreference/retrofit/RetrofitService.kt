package com.example.spacexreference.retrofit

import com.example.spacexreference.data.CrewData
import com.example.spacexreference.data.LaunchData
import retrofit2.http.GET

interface RetrofitService {

    @GET("launches")
    suspend fun getAllLaunches(): MutableList<LaunchData>

    @GET("crew")
    suspend fun getAllCrew(): MutableList<CrewData>
}