package com.example.spacexreference.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PatchData(
    @SerializedName("small")
    var smallImageUrl: String? = null,
    @SerializedName("large")
    var largeImageUrl: String? = null
) : Serializable
