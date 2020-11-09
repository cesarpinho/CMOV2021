package org.feup.cp.acme.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.CustomerAppData
import org.feup.cp.acme.network.VoucherInfoResponse
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.singleton.User
import org.feup.cp.acme.room.entity.Voucher
import org.feup.cp.acme.security.KeyStoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class VouchersRepository {

    /**
     * Endpoint for contacting the server
     */
    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

    /**
     * Updates the products data structure for display
     */
    fun getVouchers(): LiveData<List<Voucher>> {
        // Create mutable live data
        val vouchersData = MutableLiveData<List<Voucher>>()

        // Retrieve current user and construct voucher request body
        val currentUser = User.getInstance()!!.currentUser

        val requestData = CustomerAppData(currentUser.uuid,
                KeyStoreManager.signData(currentUser.uuid, KeyStoreManager.getPrivateKey(currentUser.nickname)),
                Date(System.currentTimeMillis()))

        webService.vouchers(requestData).enqueue(object : Callback<List<VoucherInfoResponse>> {
            override fun onResponse(call: Call<List<VoucherInfoResponse>>, response: Response<List<VoucherInfoResponse>>) {
                if (response.isSuccessful) {
                    // Clear previous data
                    AppDatabase.getInstance()!!.voucherDao().nukeTable()

                    // Store new updated information
                    response.body()?.forEach {
                        AppDatabase.getInstance()!!.voucherDao()
                                .insertAll(Voucher(0, it.type, it.code, it.date, currentUser.nickname))
                    }
                }
                vouchersData.value = AppDatabase.getInstance()!!.voucherDao().getAll()
            }

            override fun onFailure(call: Call<List<VoucherInfoResponse>>, t: Throwable) {
                println(t.stackTrace)
            }
        })
        return vouchersData
    }

}