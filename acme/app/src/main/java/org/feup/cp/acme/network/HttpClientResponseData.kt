package org.feup.cp.acme.network

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
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