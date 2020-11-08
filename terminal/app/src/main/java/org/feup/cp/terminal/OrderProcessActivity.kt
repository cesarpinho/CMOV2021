package org.feup.cp.terminal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderProcessActivity() : AppCompatActivity() {

    /**
     * Variables for view test
     * TODO - to be replaced when qr code reader is implemented
     */
    private var status: Boolean = true
    var orderInfo: Map<String, String> =
        mapOf("orderId" to "123549822", "voucher" to "1235", "total" to "1,00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_process)
        Toolbar(this, null)
        val icon = findViewById<ImageView>(R.id.order_status)

        if (status) {
            // If order is valid displays the order information and the OK icon
            icon.setImageResource(R.drawable.ic_correct)
            findViewById<TableLayout>(R.id.order_info).visibility = TableLayout.VISIBLE
            findViewById<TextView>(R.id.order_id).text = "#".plus(orderInfo["orderId"])
            findViewById<TextView>(R.id.total_display).text = orderInfo["total"].plus("€")

            // If the order don't have a voucher, the row is hidden
            if (orderInfo["voucher"] != "")
                findViewById<TextView>(R.id.voucher_id).text = "#".plus(orderInfo["voucher"])
            else
                findViewById<TableRow>(R.id.row_voucher).visibility = TableRow.GONE
        } else {
            icon.setImageResource(R.drawable.ic_error)
            findViewById<TextView>(R.id.order_error).visibility = TextView.VISIBLE
        }
    }

}
