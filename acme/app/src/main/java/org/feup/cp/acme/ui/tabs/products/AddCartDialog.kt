package org.feup.cp.acme.ui.tabs.products

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import org.feup.cp.acme.R
import org.feup.cp.acme.network.ProductCartInfo
import org.feup.cp.acme.room.entity.Product
import org.feup.cp.acme.singleton.Cart

class AddCartDialog(private val product: Product) : DialogFragment() {

    /**
     * Creates the dialog for selects the quantity of the product and add the product to the cart
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialog)

            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.layout_add_cart_dialog, null)

            // Add button listener to add the product to the cart
            view.findViewById<Button>(R.id.btn_dialog_add_cart).setOnClickListener {
                val quantity =
                    view.findViewById<EditText>(R.id.input_dialog_quantity).text.toString().toInt()

                Cart.getInstance()!!.insertProduct(
                    ProductCartInfo(
                        product.name,
                        quantity
                    )
                )

                // Closes the dialog
                this.dismiss()
                Toast.makeText(requireContext(), "Product Added to Cart!", Toast.LENGTH_LONG).show()
            }

            // Add listener for close the dialog and cancel the action
            view.findViewById<Button>(R.id.btn_close_dialog).setOnClickListener {
                this.dismiss()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}

