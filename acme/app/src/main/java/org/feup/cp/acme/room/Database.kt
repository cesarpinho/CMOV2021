package org.feup.cp.acme.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.feup.cp.acme.room.dao.CustomerDao
import org.feup.cp.acme.room.dao.ProductDao
import org.feup.cp.acme.room.dao.VoucherDao
import org.feup.cp.acme.room.entity.Customer
import org.feup.cp.acme.room.entity.Product
import org.feup.cp.acme.room.entity.Voucher

@Database(entities = [Customer::class, Product::class, Voucher::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Dao Classes
     */
    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao
    abstract fun voucherDao(): VoucherDao

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