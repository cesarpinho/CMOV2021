package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Customer::class, parentColumns = ["nickname"], childColumns = ["nickname"])])
data class Voucher(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val type: Boolean,
        val code: String,
        val date: Date,
        val nickname: String
)