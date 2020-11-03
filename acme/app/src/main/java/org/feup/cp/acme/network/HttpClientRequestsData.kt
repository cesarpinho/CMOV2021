package org.feup.cp.acme.network

import com.google.gson.annotations.SerializedName

data class RegisterData(
        @SerializedName("name")
        var name : String,
        @SerializedName("card")
        var card : Number,
        @SerializedName("nif")
        var nif : Number,
        @SerializedName("nickname")
        var nickname : String,
        @SerializedName("password")
        var password : String,
        @SerializedName("certificate")
        var certificate : String?
)