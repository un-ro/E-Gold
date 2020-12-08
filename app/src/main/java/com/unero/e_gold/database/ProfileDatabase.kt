package com.unero.e_gold.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.unero.e_gold.dao.ProfileDao
import com.unero.e_gold.models.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase: RoomDatabase(){
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): ProfileDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "word_database"
                ).fallbackToDestructiveMigration()
                        .addCallback(ProfileDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class ProfileDatabaseCallback(
                private val scope: CoroutineScope
        ): RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO){
                        populateDatabase(database.profileDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(profileDao: ProfileDao){
            profileDao.deleteAll()

            val profile = Profile("Unero", "ramadhanbhg@gmail.com", "")
            profileDao.insert(profile)
        }
    }
}