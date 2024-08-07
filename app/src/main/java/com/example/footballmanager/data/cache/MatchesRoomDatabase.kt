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
abstract class MatchesRoomDatabase: RoomDatabase(){
    abstract fun matchDao(): MatchDao

    companion object{
        @Volatile
        private var INSTANCE: MatchesRoomDatabase? = null

        fun getDatabase(ctx: Context): MatchesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    MatchesRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}