package com.example.footballmanager.network

import com.google.gson.annotations.SerializedName

data class FixtureDataWrapper(
    @SerializedName("response") var responseBody: ArrayList<ResponseBody> = arrayListOf()
)

data class ResponseBody(
    @SerializedName("teams") var teams: Teams? = Teams(),
    @SerializedName("goals") var goals: Goals? = Goals()
)

data class Teams(
    @SerializedName("home") var home: Home? = Home(),
    @SerializedName("away") var away: Away? = Away()
)

data class Home(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null,
    @SerializedName("winner") var winner: String? = null
)

data class Away(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null,
    @SerializedName("winner") var winner: String? = null
)

data class Goals(
    @SerializedName("home") var home: String? = null,
    @SerializedName("away") var away: String? = null
)