package org.feup.cp.terminal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.terminal.network.HttpClient
import org.feup.cp.terminal.network.HttpClientInterface
import org.feup.cp.terminal.network.OrderData
import org.feup.cp.terminal.network.OrderInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderProcessActivity() : AppCompatActivity() {

    var status: Boolean = false
    lateinit var orderId: String
    lateinit var voucher: String
    var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val encodedOrder = intent.extras?.getString("org.feup.cp.terminal.ENCODED_ORDER")
        println(encodedOrder)
        connectToServer(encodedOrder!!)

        setContentView(R.layout.activity_order_process)
        Toolbar(this, null)
        val icon = findViewById<ImageView>(R.id.order_status)

        if (status) {
            // If order is valid displays the order information and the OK icon
            icon.setImageResource(R.drawable.ic_correct)
            findViewById<TableLayout>(R.id.order_info).visibility = TableLayout.VISIBLE
            findViewById<TextView>(R.id.order_id).text = "#".plus(orderId)
            findViewById<TextView>(R.id.total_display).text = total.toString().plus("€")

            // If the order don't have a voucher, the row is hidden
            if (voucher != "")
                findViewById<TextView>(R.id.voucher_id).text = "#".plus(voucher)
            else
                findViewById<TableRow>(R.id.row_voucher).visibility = TableRow.GONE
        } else {
            icon.setImageResource(R.drawable.ic_error)
            findViewById<TextView>(R.id.order_error).visibility = TextView.VISIBLE
        }
    }

    private fun connectToServer(encodedOrder: String) {
        val order = OrderData(encodedOrder)
        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        webService.purchase(order).enqueue(object : Callback<OrderInfoResponse> {
            override fun onResponse(
                call: Call<OrderInfoResponse>,
                response: Response<OrderInfoResponse>
            ) {
                if (!response.isSuccessful) {
                    status = false
                    return
                } else {
                    val orderInfo = response.body()!!
                    status = true
                    orderId = orderInfo.order
                    voucher = orderInfo.voucher
                    total = orderInfo.total
                    return
                }
            }

            override fun onFailure(call: Call<OrderInfoResponse>, t: Throwable) {
                println(t.stackTrace)
            }
        })

    }

}
