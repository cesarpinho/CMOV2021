package org.feup.cp.acme.network

import retrofit2.Call
import retrofit2.http.GET

/**
 * REST Api Interface
 */
interface HttpClientInterface {

    @GET("posts")
    fun getPosts() : Call<List<Posts>>

}