package com.example.footballmanager.data.cache

import androidx.room.TypeConverter
import com.example.footballmanager.data.entities.Fixture
import com.example.footballmanager.data.entities.Goals
import com.example.footballmanager.data.entities.League
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
}
