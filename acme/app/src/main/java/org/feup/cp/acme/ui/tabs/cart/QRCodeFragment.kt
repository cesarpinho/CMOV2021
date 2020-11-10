package org.feup.cp.acme.ui.tabs.cart

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import org.feup.cp.acme.R
import org.feup.cp.acme.singleton.Cart

class QRCodeFragment : Fragment() {

    var qrCodeInitialized: Boolean = false
    private lateinit var qrCode: Bitmap
    private lateinit var imageView: ImageView

    /**
     * Creates the QRCode tab view of the cart
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qrcode, container, false)

        if (!qrCodeInitialized) {
            qrCode = encodeToQrCode(Cart.getInstance()!!.encodeToString(), inflater.context)
            qrCodeInitialized = true
            println("Create qr code!!")
        }
        imageView = view.findViewById(R.id.qrcode_image)
        imageView.setImageBitmap(qrCode)

        return view
    }


    /**
     * Creates a QR code in bitmap format
     */
    private fun encodeToQrCode(text: String?, context: Context): Bitmap {
        val width = 500;
        val height = 500
        var matrix: BitMatrix? = null
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        try {
            matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height)
        } catch (ex: WriterException) {
            ex.printStackTrace()
        }

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x, y,
                    if (matrix!![x, y]) context.getColor(R.color.colorBlack)
                    else context.getColor(R.color.colorBeige)
                )
            }
        }
        return bitmap
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = QRCodeFragment()
    }
}
