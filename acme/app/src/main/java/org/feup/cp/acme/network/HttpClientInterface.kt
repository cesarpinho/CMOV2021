package org.feup.cp.acme.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * REST Api Interface
 */
interface HttpClientInterface {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(@Body body: RegisterData): Call<CustomerInfoResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body body: LoginData): Call<CustomerInfoResponse>

}