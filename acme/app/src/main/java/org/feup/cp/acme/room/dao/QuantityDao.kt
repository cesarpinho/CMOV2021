package org.feup.cp.acme.room.dao

import androidx.room.*
import org.feup.cp.acme.room.entity.Quantity

@Dao
interface QuantityDao {
    @Insert
    fun insertAll(vararg quantities: Quantity)

    @Update
    fun updateAll(vararg quantities: Quantity)

    @Delete
    fun deleteAll(vararg quantities: Quantity)

    @Query("SELECT * FROM quantity")
    fun getAll(): List<Quantity>

    @Query("DELETE FROM quantity")
    fun nukeTable()
}