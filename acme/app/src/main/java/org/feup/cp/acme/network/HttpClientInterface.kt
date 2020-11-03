package org.feup.cp.acme.network

import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * REST Api Interface
 */
interface HttpClientInterface {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(@Body body: RegisterData): Call<RegisterResponse>

}