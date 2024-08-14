package com.example.footballmanager.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class MatchEventsDataWrapper(
    @SerializedName("response") var matchEvents: ArrayList<MatchEvents> = arrayListOf()
)

data class MatchEvents(
    @SerializedName("time") var time: Time? = null,
    @SerializedName("team") var team: Team? = null,
    @SerializedName("player") var player: Player? = null,
    @SerializedName("type") var type: String? = null

)

data class Time(
    @SerializedName("elapsed") var elapsed: Int? = null
)

data class Team(
    @SerializedName("name") var name: String? = null
)

data class Player(
    @SerializedName("name") var name: String? = null
)