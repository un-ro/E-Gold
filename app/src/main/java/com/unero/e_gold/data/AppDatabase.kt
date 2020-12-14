package com.unero.e_gold.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unero.e_gold.data.dao.ProfileDao
import com.unero.e_gold.data.dao.TransactionDao
import com.unero.e_gold.models.Profile
import com.unero.e_gold.models.Transaction
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Profile::class, Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    // Dao
    abstract fun profileDao(): ProfileDao
    abstract fun transacDao(): TransactionDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}