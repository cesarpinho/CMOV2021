package org.feup.cp.terminal

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QRCodeActivity : AppCompatActivity() {

    /**
     * Creates the qr code reader activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        Toolbar(this, "qr_code")

        // TODO - To be replaced
        findViewById<TextView>(R.id.qr_reader).setOnClickListener { _ ->
            val intent = Intent(this, OrderProcessActivity::class.java)
            startActivity(intent)
        }
    }
}
