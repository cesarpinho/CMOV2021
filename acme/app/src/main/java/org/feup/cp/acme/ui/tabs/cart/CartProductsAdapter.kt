package org.feup.cp.acme.ui.tabs.cart

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.feup.cp.acme.R
import org.feup.cp.acme.singleton.Cart
import org.feup.cp.acme.singleton.CartData

class CartProductsAdapter(private val dataSet: CartData, private val totalView: TextView) :
    RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    /**
     * Cart product View holder class
     */
    class ViewHolder(relativeLayout: RelativeLayout) : RecyclerView.ViewHolder(relativeLayout)

    /**
     * Creates card view holder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    /**
     * Binds a view holder / card with each product
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = holder.itemView
        val quantityInput = card.findViewById<EditText>(R.id.input_quantity)
        val removeBtn = card.findViewById<Button>(R.id.btn_remove)

        // Load the image
        Picasso.get().load(dataSet.products[position].icon)
            .into(card.findViewById<ImageView>(R.id.card_image))
        card.findViewById<TextView>(R.id.card_title).text = dataSet.products[position].name
        card.findViewById<TextView>(R.id.card_subtitle).text = dataSet.products[position].price.toString().plus("$")
        quantityInput.visibility = EditText.VISIBLE
        quantityInput.setText(dataSet.products[position].quantity.toString())

        totalView.text = StringBuilder("Total: ".plus(Cart.getInstance()!!.getCartData().value!!.total.toString()).plus("$"))

        // Add listener to update the total when th quantity is changed
        quantityInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    Cart.getInstance()!!.updateProductQuantity(dataSet.products[position].name, "$s".toInt())
                    totalView.text = StringBuilder("Total: ".plus(Cart.getInstance()!!.getCartData().value!!.total.toString()).plus("$"))
                }
            }
        })

        removeBtn.visibility = Button.VISIBLE
        removeBtn.setOnClickListener {
            Cart.getInstance()!!.removeProduct(dataSet.products[position].name)
            totalView.text = StringBuilder("Total: ".plus(Cart.getInstance()!!.getCartData().value!!.total.toString()).plus("$"))
            this.notifyDataSetChanged()
        }
    }

    /**
     * Returns the quantity of card views
     */
    override fun getItemCount() = dataSet.products.size
}
