package org.feup.cp.terminal.network

import com.google.gson.annotations.SerializedName

data class OrderInfoResponse(
        @SerializedName("orderId")
        var order: String,
        @SerializedName("voucher")
        var voucher: String,
        @SerializedName("total")
        var total: Double,
)