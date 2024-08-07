package com.example.footballmanager.data.cache

const val DATABASE_NAME = "word_database"

//@Database(entities = [FixtureDataWrapper::class], version = 1, exportSchema = false)
//abstract class FixturesRoomDatabase: RoomDatabase(){
//    abstract fun fixtureDao(): FixtureDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: FixturesRoomDatabase? = null
//
//        fun getDatabase(ctx: Context): FixturesRoomDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    ctx.applicationContext,
//                    FixturesRoomDatabase::class.java,
//                    DATABASE_NAME
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}