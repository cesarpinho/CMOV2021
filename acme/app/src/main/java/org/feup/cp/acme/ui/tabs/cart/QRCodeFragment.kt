package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R

class QRCodeFragment : Fragment() {

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_qrcode,container,false)
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = QRCodeFragment()
    }
}
