package com.example.footballmanager.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballmanager.DATABASE_NAME
import com.example.footballmanager.data.entities.Match

@Database(entities = [Match::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MatchesRoomDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}