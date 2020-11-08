package org.feup.cp.acme.network

import com.google.gson.annotations.SerializedName

data class CustomerInfoResponse(
        @SerializedName("uuid")
        var uuid : String,
        @SerializedName("name")
        var name : String,
        @SerializedName("card")
        var card : Number,
        @SerializedName("nif")
        var nif : Number,
        @SerializedName("nickname")
        var nickname : String
)

data class ProductInfoResponse(
        @SerializedName("type")
        var type : String,
        @SerializedName("icon")
        var icon : String,
        @SerializedName("name")
        var name : String,
        @SerializedName("price")
        var price : Double
)