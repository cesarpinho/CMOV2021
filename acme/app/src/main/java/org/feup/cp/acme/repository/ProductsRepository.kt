package org.feup.cp.acme.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.ProductInfoResponse
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.room.entity.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository() {

    /**
     * Endpoint for contacting the server
     */
    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

    /**
     * Updates the products data structure for display
     */
    fun getProducts(): LiveData<List<Product>> {
        // Create mutable live data
        val productsData = MutableLiveData<List<Product>>()

        // Check current version of products
        val version = AppDatabase.getInstance()!!.productDao().getVersion()

        webService.products(version).enqueue(object : Callback<List<ProductInfoResponse>> {
            override fun onResponse(call: Call<List<ProductInfoResponse>>, response: Response<List<ProductInfoResponse>>) {
                if (response.isSuccessful) {
                    // Clear previous data
                    AppDatabase.getInstance()!!.quantityDao().nukeTable()
                    AppDatabase.getInstance()!!.productDao().nukeTable()

                    // Store new updated information
                    response.body()?.forEach {
                        AppDatabase.getInstance()!!.productDao()
                                .insertAll(Product(0, it.type, it.icon, it.name, it.price))
                    }
                }
                productsData.value = AppDatabase.getInstance()!!.productDao().getAll()
            }

            override fun onFailure(call: Call<List<ProductInfoResponse>>, t: Throwable) {
                println(t.stackTrace)
            }
        })

        return productsData
    }

}