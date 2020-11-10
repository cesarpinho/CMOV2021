package org.feup.cp.terminal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderProcessActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val encodedOrder = intent.extras?.getString("org.feup.cp.terminal.ENCODED_ORDER")
        println(encodedOrder)
        // TODO - Send encoded order to server
        // TODO - Get response and init val's
        val status = true
        val orderId = "123549822"
        val voucher = "1235"
        val total = "1,00"

        setContentView(R.layout.activity_order_process)
        Toolbar(this, null)
        val icon = findViewById<ImageView>(R.id.order_status)

        if (status) {
            // If order is valid displays the order information and the OK icon
            icon.setImageResource(R.drawable.ic_correct)
            findViewById<TableLayout>(R.id.order_info).visibility = TableLayout.VISIBLE
            findViewById<TextView>(R.id.order_id).text = "#".plus(orderId)
            findViewById<TextView>(R.id.total_display).text = total.plus("€")

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

}
