package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Product(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val type: String,
        val icon: String,
        val name: String,
        val price: Double,
        val version: Date = Date(System.currentTimeMillis())
)