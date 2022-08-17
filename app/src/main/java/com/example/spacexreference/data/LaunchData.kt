package com.example.spacexreference.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class LaunchData(
    var name: String? = null,
    @SerializedName("success")
    var missionStatus: Boolean = false,
    var cores: List<CoreData> = listOf(),
    @SerializedName("date_utc")
    var dateUtc: Date? = null,
    @SerializedName("links")
    var linkData: LinkData? = null,
    var details: String? = null,
    var id: String? = null
) : Serializable {

    val largeLaunchIcon: String?
        get() = linkData?.patchData?.largeImageUrl
}
