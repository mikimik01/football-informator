package com.example.footballmanager.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballmanager.DATABASE_NAME
import com.example.footballmanager.data.entities.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM $DATABASE_NAME")
    suspend fun getCachedMatches(): List<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matches: List<Match>)
}