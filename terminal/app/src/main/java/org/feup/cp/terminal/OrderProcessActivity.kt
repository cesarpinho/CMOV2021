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

    /**
     * Icon image view instance
     */
    lateinit var icon: ImageView

    /**
     * Creates the order process activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_process)
        Toolbar(this, null)
        icon = findViewById(R.id.order_status)

        val encodedOrder = intent.extras?.getString("org.feup.cp.terminal.ENCODED_ORDER")
        connectToServer(encodedOrder!!)
    }

    /**
     * Creates the connection with the server and updates view with the response
     */
    private fun connectToServer(encodedOrder: String) {
        val order = OrderData(encodedOrder)
        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        webService.purchase(order).enqueue(object : Callback<OrderInfoResponse> {
            override fun onResponse(
                call: Call<OrderInfoResponse>,
                response: Response<OrderInfoResponse>
            ) {
                if (!response.isSuccessful) {
                    icon.setImageResource(R.drawable.ic_error)
                    findViewById<TextView>(R.id.order_error).visibility = TextView.VISIBLE
                } else {
                    val orderInfo = response.body()!!

                    // If order is valid displays the order information and the OK icon
                    icon.setImageResource(R.drawable.ic_correct)
                    findViewById<TableLayout>(R.id.order_info).visibility = TableLayout.VISIBLE
                    findViewById<TextView>(R.id.order_id).text = "#".plus(orderInfo.order)
                    findViewById<TextView>(R.id.total_display).text =
                        String.format("%.2f",orderInfo.total).plus("$")

                    // If the order don't have a voucher, the row is hidden
                    if (orderInfo.voucherCode != null)
                        findViewById<TextView>(R.id.voucher_id).text =
                            "#".plus(orderInfo.voucherCode)
                                .plus(" (${if (orderInfo.voucherType) "5% Discount" else "Free Extra Coffee"})")
                    else
                        findViewById<TableRow>(R.id.row_voucher).visibility = TableRow.GONE
                }

            }

            override fun onFailure(call: Call<OrderInfoResponse>, t: Throwable) {
                icon.setImageResource(R.drawable.ic_error)
                findViewById<TextView>(R.id.order_error).visibility = TextView.VISIBLE
                println(t.stackTrace)
            }
        })

    }

}
