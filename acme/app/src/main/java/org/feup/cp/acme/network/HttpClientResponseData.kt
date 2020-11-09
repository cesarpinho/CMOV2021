package org.feup.cp.acme.network

import com.google.gson.annotations.SerializedName
import java.util.*

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

data class VoucherInfoResponse(
        @SerializedName("type")
        var type : Boolean,
        @SerializedName("code")
        var code : String,
        @SerializedName("date")
        var date : Date
)

data class ReceiptInfoResponse(
        @SerializedName("code")
        var code : String,
        @SerializedName("date")
        var date : Date,
        @SerializedName("total")
        var total : Double,
        @SerializedName("products")
        var products : List<ProductQuantityInfo>,
)

data class ProductQuantityInfo(
        @SerializedName("type")
        var type : String,
        @SerializedName("name")
        var name : String,
        @SerializedName("quantity")
        var quantity : Int,
        @SerializedName("icon")
        var icon : String,
        @SerializedName("price")
        var price: Double
)