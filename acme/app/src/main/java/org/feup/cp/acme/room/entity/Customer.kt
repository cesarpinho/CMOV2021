package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity(indices = [Index(value = ["nickname"], unique = true)])
data class Customer(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val uuid: String,
        val name: String,
        val card: Double,
        val nif: Int,
        val nickname: String
)