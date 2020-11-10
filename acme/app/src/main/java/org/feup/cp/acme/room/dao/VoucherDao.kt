package org.feup.cp.acme.room.dao

import androidx.room.*
import org.feup.cp.acme.room.entity.Voucher

@Dao
interface VoucherDao {
    @Insert
    fun insertAll(vararg vouchers: Voucher)

    @Update
    fun updateAll(vararg vouchers: Voucher)

    @Delete
    fun deleteAll(vararg vouchers: Voucher)

    @Query("SELECT * FROM voucher")
    fun getAll(): List<Voucher>

    @Query("SELECT * FROM voucher WHERE code = :code")
    fun has(code: String): List<Voucher>

    @Query("DELETE FROM voucher")
    fun nukeTable()
}