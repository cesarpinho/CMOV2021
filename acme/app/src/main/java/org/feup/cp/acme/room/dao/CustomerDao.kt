package org.feup.cp.acme.room.dao

import androidx.room.*
import org.feup.cp.acme.room.entity.Customer


@Dao
interface CustomerDao {
    @Insert
    fun insertAll(vararg customers: Customer)

    @Update
    fun updateAll(vararg customers: Customer)

    @Delete
    fun deleteAll(vararg customers: Customer)

    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query("DELETE FROM customer")
    fun nukeTable()

}