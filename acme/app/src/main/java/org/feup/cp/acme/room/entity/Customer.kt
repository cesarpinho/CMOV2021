package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey val id: Int,
    val uuid: String,
    val name: String,
    val card: Int,
    val nif: Int,
    val nickname: String // TODO - Add unique constraint
)