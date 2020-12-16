package com.unero.e_gold.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unero.e_gold.data.dao.AccountDao
import com.unero.e_gold.data.dao.TransactDao
import com.unero.e_gold.data.model.Account
import com.unero.e_gold.data.model.Transact
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Account::class, Transact::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    // DAO Object
    abstract fun accountDao(): AccountDao
    abstract fun transactDao(): TransactDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): AppDatabase{
            val temp = INSTANCE
            if (temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gold_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}