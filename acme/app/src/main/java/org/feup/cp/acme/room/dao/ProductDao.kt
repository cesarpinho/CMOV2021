package org.feup.cp.acme.room.dao

import androidx.room.*
import org.feup.cp.acme.room.entity.Product
import java.util.*

@Dao
interface ProductDao {
    @Insert
    fun insertAll(vararg products: Product)

    @Update
    fun updateAll(vararg products: Product)

    @Delete
    fun deleteAll(vararg products: Product)

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT version FROM product ORDER BY version LIMIT 1")
    fun getVersion(): Date

    @Query("DELETE FROM product")
    fun nukeTable()
}