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

class QRCodeFragment : Fragment() {

    /**
     * Creates the QRCode tab view of the cart
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qrcode, container, false)
        view.findViewById<ImageView>(R.id.qrcode_image).setImageBitmap(
            encodeToQrCode("test 1 2 3", inflater.context)
        )

        return view
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = QRCodeFragment()
    }

    /**
     * Creates a QR code in bitmap format
     */
    // TODO - Include in cart class
    private fun encodeToQrCode(text: String?, context: Context): Bitmap? {
        val width = 500
        val height = 500
        var matrix: BitMatrix? = null
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        try {
            matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height)
        } catch (ex: WriterException) {
            ex.printStackTrace()
        }

        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(
                    x, y,
                    if (matrix!![x, y]) context.getColor(R.color.colorBlack)
                    else context.getColor(R.color.colorBeige)
                )
            }
        }
        return bmp
    }
}
