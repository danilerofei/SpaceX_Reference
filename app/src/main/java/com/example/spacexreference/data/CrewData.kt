package com.example.spacexreference.data

data class CrewData(
    var name: String? = null,
    var agency: String? = null,
    var launches: ArrayList<String?>? = null,
    var status: String? = null
)
