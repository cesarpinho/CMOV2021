package org.feup.cp.terminal.network

import com.google.gson.annotations.SerializedName

data class OrderData(
        @SerializedName("order")
        var order: String
)
