package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
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
        val view = inflater.inflate(R.layout.fragment_qrcode,container,false)

        // TODO - Add QRcode to the view
        view.findViewById<ImageView>(R.id.qrcode_image).setImageResource(R.drawable.ic_discount)

        return view
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = QRCodeFragment()
    }
}
