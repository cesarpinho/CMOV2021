package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Customer::class, parentColumns = ["nickname"], childColumns = ["nickname"])])
data class Voucher(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val type: Boolean,
        val date: Date = Date(System.currentTimeMillis()),
        val nickname: String
)