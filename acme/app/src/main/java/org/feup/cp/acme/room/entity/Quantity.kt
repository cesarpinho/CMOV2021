package org.feup.cp.acme.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["id_receipt", "id_product"], foreignKeys = [
    ForeignKey(entity = Receipt::class, parentColumns = ["id"], childColumns = ["id_receipt"]),
    ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["id_product"])
])
data class Quantity (
        val id_receipt: Long,
        val id_product: Long,
        val quantity: Int
)