package com.example.footballmanager.network

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class FixtureDataWrapper(
    @SerializedName("response") var responseBody: ArrayList<ResponseBody> = arrayListOf()
)

data class ResponseBody(
    @SerializedName("teams") var teams: Teams? = Teams(),
    @SerializedName("goals") var goals: Goals? = Goals(),
    @SerializedName("league") var league: League? = League(),
    @SerializedName("fixture") var fixture: Fixture? = Fixture()
)

data class League(
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null
)

data class Fixture(
    @SerializedName("status") var status: Status? = Status()
)

data class Status(
    @SerializedName("short") var short: String? = null
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