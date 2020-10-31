package org.feup.cp.acme

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Posts(
        @SerializedName("userId")
        var userId : Int,
        @SerializedName("id")
        var id : Int,
        @SerializedName("title")
        var title : String,
        @SerializedName("body")
        var body : String
)

interface Endpoint {

    @GET("posts")
    fun getPosts() : Call<List<Posts>>

    @GET("test/api")
    fun getLocal() : Call<String>
}

class NetworkUtils {

    companion object {

        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */
        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}