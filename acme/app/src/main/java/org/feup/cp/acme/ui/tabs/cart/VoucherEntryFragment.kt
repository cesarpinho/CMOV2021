package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.singleton.Cart
import org.feup.cp.acme.ui.CartActivity

class VoucherEntryFragment(
    /**
     * Cart activity instance
     */
    private val cartActivity: CartActivity
) : Fragment() {

    /**
     * Creates the voucher tab view of the cart
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voucher_entry, container, false)
        val voucherInput = view.findViewById<EditText>(R.id.input_voucher)

        voucherInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                if (voucherInput.text.length < VOUCHER_SIZE + 1)
                    voucherInput.error = "Voucher has 9 characters\nOnly can insert one voucher per purchase" // TODO - Update validation message
        }

        view.findViewById<Button>(R.id.btn_complete).setOnClickListener {
            val voucher = voucherInput.text.toString()
            println(voucher)
            if (voucher.isNotEmpty() && voucher.length > VOUCHER_SIZE + 1)
                voucherInput.error = "Voucher has 9 characters\nOnly can insert one voucher per purchase" // TODO - Update validation message
            else if (voucher.isNotBlank()) {
                if(AppDatabase.getInstance()!!.voucherDao().has(voucher).isNotEmpty()) {
                    Cart.getInstance()!!.setVoucher(voucher)

                    println(Cart.getInstance()!!.encodeToString()) // String for the QR Code
                    cartActivity.addQRCodeTab() // TODO - Generate string for qr code
                }
                else
                    println("Invalid voucher") // TODO - Display on screen the voucher error invalidity
            }
            else {
                println(Cart.getInstance()!!.encodeToString()) // String for the QR Code
                cartActivity.addQRCodeTab() // TODO - Generate string for qr code
            }
        }
        return view
    }

    /**
     * Static functions
     */
    companion object {

        /**
         * Voucher length constant
         */
        const val VOUCHER_SIZE = 8

        @JvmStatic
        fun newInstance(cartActivity: CartActivity) = VoucherEntryFragment(cartActivity)
    }
}
