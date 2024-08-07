package com.example.footballmanager.data.cache

import androidx.room.TypeConverter
import com.example.footballmanager.data.entities.Away
import com.example.footballmanager.data.entities.Fixture
import com.example.footballmanager.data.entities.Goals
import com.example.footballmanager.data.entities.Home
import com.example.footballmanager.data.entities.League
import com.example.footballmanager.data.entities.Status
import com.example.footballmanager.data.entities.Teams
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTeams(teams: Teams?): String {
        return gson.toJson(teams)
    }

    @TypeConverter
    fun toTeams(teamsString: String): Teams? {
        val type = object : TypeToken<Teams>() {}.type
        return gson.fromJson(teamsString, type)
    }

    @TypeConverter
    fun fromGoals(goals: Goals?): String {
        return gson.toJson(goals)
    }

    @TypeConverter
    fun toGoals(goalsString: String): Goals? {
        val type = object : TypeToken<Goals>() {}.type
        return gson.fromJson(goalsString, type)
    }

    @TypeConverter
    fun fromLeague(league: League?): String {
        return gson.toJson(league)
    }

    @TypeConverter
    fun toLeague(leagueString: String): League? {
        val type = object : TypeToken<League>() {}.type
        return gson.fromJson(leagueString, type)
    }

    @TypeConverter
    fun fromFixture(fixture: Fixture?): String {
        return gson.toJson(fixture)
    }

    @TypeConverter
    fun toFixture(fixtureString: String): Fixture? {
        val type = object : TypeToken<Fixture>() {}.type
        return gson.fromJson(fixtureString, type)
    }

    @TypeConverter
    fun fromStatus(status: Status?): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun toStatus(statusString: String): Status? {
        val type = object : TypeToken<Status>() {}.type
        return gson.fromJson(statusString, type)
    }

    @TypeConverter
    fun fromHome(home: Home?): String {
        return gson.toJson(home)
    }

    @TypeConverter
    fun toHome(homeString: String): Home? {
        val type = object : TypeToken<Home>() {}.type
        return gson.fromJson(homeString, type)
    }

    @TypeConverter
    fun fromAway(away: Away?): String {
        return gson.toJson(away)
    }

    @TypeConverter
    fun toAway(awayString: String): Away? {
        val type = object : TypeToken<Away>() {}.type
        return gson.fromJson(awayString, type)
    }
}
