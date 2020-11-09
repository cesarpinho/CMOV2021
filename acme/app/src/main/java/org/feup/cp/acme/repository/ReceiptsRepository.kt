package org.feup.cp.acme.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.feup.cp.acme.network.*
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.singleton.User
import org.feup.cp.acme.room.entity.Product
import org.feup.cp.acme.room.entity.Quantity
import org.feup.cp.acme.room.entity.Receipt
import org.feup.cp.acme.security.KeyStoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ReceiptsRepository {

    /**
     * Endpoint for contacting the server
     */
    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

    /**
     * Updates the products data structure for display
     */
    fun getReceipts(): LiveData<List<ReceiptInfoResponse>> {
        // Create mutable live data
        val receiptsData = MutableLiveData<List<ReceiptInfoResponse>>()

        // Retrieve current user and construct voucher request body
        val currentUser = User.getInstance()!!.currentUser

        val requestData = CustomerAppData(currentUser.uuid,
                KeyStoreManager.signData(currentUser.uuid, KeyStoreManager.getPrivateKey(currentUser.nickname)),
                Date(System.currentTimeMillis()))

        webService.receipts(requestData).enqueue(object : Callback<List<ReceiptInfoResponse>> {
            override fun onResponse(call: Call<List<ReceiptInfoResponse>>, response: Response<List<ReceiptInfoResponse>>) {
                if (response.isSuccessful) {
                    // Clear previous data
                    val db = AppDatabase.getInstance()!!
                    db.quantityDao().nukeTable()
                    db.receiptsDao().nukeTable()

                    // Store new updated information
                    response.body()?.forEach {
                        val receiptId = db.receiptsDao().insert(Receipt(0, it.date, it.code, it.total, currentUser.nickname))

                        it.products.forEach { product ->
                            var productId = db.productDao().getId(product.name)

                            if(productId.compareTo(0) == 0)
                                productId = db.productDao().insert(Product(0, product.type, product.icon, product.name, product.price, Date(2020)))

                            db.quantityDao().insertAll(Quantity(receiptId, productId, product.quantity))
                        }
                    }
                    receiptsData.value = response.body()
                }

            }

            override fun onFailure(call: Call<List<ReceiptInfoResponse>>, t: Throwable) {
                println(t.stackTrace)
            }
        })
        return receiptsData
    }
}