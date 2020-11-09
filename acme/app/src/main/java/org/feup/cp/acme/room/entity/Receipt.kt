package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Customer::class, parentColumns = ["nickname"], childColumns = ["nickname"])],
        indices = [Index(value = ["code"], unique = true)]
        )
data class Receipt (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: Date,
    val code: String,
    val total: Double,
    val nickname: String
)