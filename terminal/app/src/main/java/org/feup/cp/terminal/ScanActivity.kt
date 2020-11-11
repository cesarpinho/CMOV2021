package org.feup.cp.terminal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CompoundBarcodeView


class ScanActivity : AppCompatActivity() {

    /**
     * Scanner view instance
     */
    lateinit var barcodeView: CompoundBarcodeView

    /**
     * Creates the qr code reader activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        Toolbar(this, "qr_code")

        barcodeView = findViewById(R.id.qr_reader)
        barcodeView.decodeContinuous(callback)
        barcodeView.setStatusText("Place the QR Code to scan it")
    }

    /**
     * Redirects to order process activity when read a QR code
     */
    private val callback: BarcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(encodedOrder: BarcodeResult) {
            if (encodedOrder.text != null) {
                val intent = Intent(this@ScanActivity, OrderProcessActivity::class.java)
                intent.putExtra("org.feup.cp.terminal.ENCODED_ORDER", encodedOrder.text)
                startActivity(intent)
            }
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

    override fun onResume() {
        barcodeView.resume()
        super.onResume()
    }

    override fun onPause() {
        barcodeView.pause()
        super.onPause()
    }
}
