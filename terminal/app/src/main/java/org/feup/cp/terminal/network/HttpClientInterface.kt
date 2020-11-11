package org.feup.cp.terminal.network

import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * REST Api Interface
 */
interface HttpClientInterface {
    @Headers("Content-Type: application/json")
    @POST("purchase")
    fun purchase(@Body body: OrderData): Call<OrderInfoResponse>
}
