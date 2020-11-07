package org.feup.cp.acme.repository

import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface

class ProductsRepository {

    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

    fun getProducts() {

    }

}