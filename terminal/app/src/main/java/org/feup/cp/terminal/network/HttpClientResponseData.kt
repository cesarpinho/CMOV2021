package org.feup.cp.terminal.network

import com.google.gson.annotations.SerializedName

data class OrderInfoResponse(
        @SerializedName("orderId")
        var order: String,
        @SerializedName("voucherCode")
        var voucherCode: String,
        @SerializedName("voucherType")
        var voucherType: Boolean,
        @SerializedName("total")
        var total: Double,
)