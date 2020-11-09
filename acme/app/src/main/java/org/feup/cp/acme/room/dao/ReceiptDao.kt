package org.feup.cp.acme.room.dao

import androidx.room.*
import org.feup.cp.acme.room.entity.Receipt

@Dao
interface ReceiptDao {
    @Insert
    fun insert(receipt: Receipt): Long

    @Insert
    fun insertAll(vararg receipts: Receipt)

    @Update
    fun updateAll(vararg receipts: Receipt)

    @Delete
    fun deleteAll(vararg receipts: Receipt)

    @Query("SELECT * FROM receipt")
    fun getAll(): List<Receipt>

    @Query("DELETE FROM receipt")
    fun nukeTable()
}