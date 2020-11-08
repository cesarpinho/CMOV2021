package org.feup.cp.acme.network

import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * REST Api Interface
 */
interface HttpClientInterface {

    @GET("products")
    fun products(@Query("version") version: Date?): Call<List<ProductInfoResponse>>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body body: LoginData): Call<CustomerInfoResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(@Body body: RegisterData): Call<CustomerInfoResponse>

}