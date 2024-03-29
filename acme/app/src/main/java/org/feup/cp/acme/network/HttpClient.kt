package org.feup.cp.acme.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient() {

    /**
     * Url used for the development environment
     */
    private val devBaseUrl: String = "http://192.168.1.166:3000"

    /**
     * Url used for the production environment
     */
    private val prodBaseUrl: String = "https://immense-thicket-06929.herokuapp.com"

    /**
     * Retrofit instance
     */
    private val httpClient: Retrofit

    /**
     * Http client endpoint used for communication
     */
    private val httpClientEndpoint: HttpClientInterface

    /**
     * Primary constructor
     */
    init {
        this.httpClient = Retrofit.Builder()
                                .baseUrl(prodBaseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

        this.httpClientEndpoint = this.httpClient.create(HttpClientInterface::class.java)
    }

    /**
     * Retrieves the endpoint for communication purposes
     */
    fun getEndpoint(): HttpClientInterface {
        return this.httpClientEndpoint
    }

    /**
     * Static functions
     */
    companion object {
        var uniqueInstance: HttpClient? = null

        fun getInstance() : HttpClient? {
            if(this.uniqueInstance == null) {
                synchronized(lock = true) {
                    if(this.uniqueInstance == null) {
                        this.uniqueInstance = HttpClient()
                    }
                }
            }
            return this.uniqueInstance
        }
    }
}

