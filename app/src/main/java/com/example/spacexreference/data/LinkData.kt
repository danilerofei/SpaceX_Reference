package com.example.spacexreference.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinkData(
    @SerializedName("patch")
    var patchData: PatchData? = null
) : Serializable
