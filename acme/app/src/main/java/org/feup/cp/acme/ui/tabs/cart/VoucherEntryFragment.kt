package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.CartActivity

class VoucherEntryFragment(private val cartActivity: CartActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voucher_entry, container, false)
        cartActivity.addQRCodeTab()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(cartActivity: CartActivity) = VoucherEntryFragment(cartActivity)
    }
}
