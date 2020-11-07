package org.feup.cp.acme.ui.tabs.products

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import org.feup.cp.acme.R

class AddCartDialog(private val idProduct: Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialog)

            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.layout_add_cart_dialog, null)

            view.findViewById<Button>(R.id.btn_dialog_add_cart).setOnClickListener { listenerView ->
                val quantity =
                    view.findViewById<EditText>(R.id.input_dialog_quantity).text.toString().toInt()
                println("Id Product: $idProduct\nQuantity: $quantity")
//                TODO("Save product and quantity on cart")
                this.dismiss()
            }
            view.findViewById<Button>(R.id.btn_close_dialog).setOnClickListener { listenerView ->
                this.dismiss()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}

