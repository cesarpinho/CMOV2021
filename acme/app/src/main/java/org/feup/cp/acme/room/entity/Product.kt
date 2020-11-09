package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Product(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val type: String,
        val icon: String,
        val name: String,
        val price: Double,
        val version: Date = Date(System.currentTimeMillis())
)