package com.example.footballmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballmanager.DATABASE_NAME
import com.google.gson.annotations.SerializedName

data class MatchDataWrapper(
    @SerializedName("response") var matches: ArrayList<Match> = arrayListOf()
)

@Entity(tableName = DATABASE_NAME)
data class Match(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    @SerializedName("id") var id: Int? = null,
    @SerializedName("status") var status: Status? = Status(),
    @SerializedName("date") var date: String? = null
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
    @SerializedName("home") var home: Int? = null,
    @SerializedName("away") var away: Int? = null
)