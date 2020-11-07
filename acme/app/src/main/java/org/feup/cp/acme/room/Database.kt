package org.feup.cp.acme.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.feup.cp.acme.room.dao.CustomerDao
import org.feup.cp.acme.room.entity.Customer

@Database(entities = [Customer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Dao Classes
     */
    abstract fun customerDao(): CustomerDao

    /**
     * Static functions
     */
    companion object {

        /**
         * Database instance
         */
        var instance: AppDatabase? = null

        /**
         * Returns database instance
         */
        fun getInstance(context: Context? = null): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context?.applicationContext!!, AppDatabase::class.java, "ACME-DB").allowMainThreadQueries().build()
                }
            }
            return instance
        }

        /**
         * Destroys database instance
         */
        fun destroyDatabaseInstance(){
            instance = null
        }

        /**
         * Destroys database
         */
        fun nukeDatabase(context: Context) {
            context.deleteDatabase("ACME-DB")
        }
    }
}