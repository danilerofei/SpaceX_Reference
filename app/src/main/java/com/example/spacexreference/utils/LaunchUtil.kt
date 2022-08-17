package com.example.spacexreference.utils

import com.example.spacexreference.data.CrewData
import com.example.spacexreference.data.LaunchData
import com.example.spacexreference.retrofit.RetrofitService
import dagger.Module
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@Module
class LaunchUtil @Inject constructor(
    private val service: RetrofitService
) {

    private val startDate: Date by lazy {
        val localDate = LocalDate.of(2021, 1, 1)
        return@lazy Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    suspend fun getLaunches(): List<LaunchData> {
        return service.getAllLaunches().sortedBy {
            it.dateUtc
        }.filter {
            (it.dateUtc?.time ?: 0) >= startDate.time
        }
    }

    suspend fun getCrew(id: String): List<CrewData> {
        return service.getAllCrew().filter {
            it.launches?.contains(id) ?: false
        }
    }
}