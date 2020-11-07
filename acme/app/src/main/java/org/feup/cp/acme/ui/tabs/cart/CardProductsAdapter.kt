package org.feup.cp.acme.ui.tabs.cart

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.feup.cp.acme.R

class CardProductsAdapter(private val totalView: TextView) :
    RecyclerView.Adapter<CardProductsAdapter.ViewHolder>() {
    class ViewHolder(relativeLayout: RelativeLayout) : RecyclerView.ViewHolder(relativeLayout)

    var totalCalc: MutableMap<String, Int> = mutableMapOf()

    //    TODO - Replace with Cart info
    val products = mutableListOf(
        hashMapOf(
            "id" to "1",
            "image" to "https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg",
            "name" to "Coffee A",
            "price" to "0.5€",
            "quantity" to "2"
        ),
        hashMapOf(
            "id" to "2",
            "image" to "https://pbs.twimg.com/profile_images/703891712427425793/KF1zgVqx.jpg",
            "name" to "Coffee B",
            "price" to "1.25€",
            "quantity" to "1"
        )
    )

    init {
        products.forEach { totalCalc[it["id"]!!] = it["quantity"]!!.toInt() }
        calculateTotal()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = holder.itemView
        val quantityInput = card.findViewById<EditText>(R.id.input_quantity)
        val removeBtn = card.findViewById<Button>(R.id.btn_remove)

        Picasso.get().load(products[position]["image"])
            .into(card.findViewById<ImageView>(R.id.card_image))
        card.findViewById<TextView>(R.id.card_title).text = products[position]["name"]
        card.findViewById<TextView>(R.id.card_subtitle).text = products[position]["price"]
        quantityInput.visibility = EditText.VISIBLE
        quantityInput.setText(products[position]["quantity"])
        quantityInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    totalCalc[products[position]["id"]!!] = "$s".toInt()
                    calculateTotal()
                }
            }
        })
        removeBtn.visibility = Button.VISIBLE
        removeBtn.setOnClickListener { view ->
            products.removeAt(position)
            this.notifyDataSetChanged()
        }
    }

    private fun calculateTotal() {
        var newTotal = 0F

        products.forEach {
            var price = it["price"]!!
            price = price.substring(0, price.length - 1)
            newTotal += (totalCalc[it["id"]]!! * price.toFloat())
        }

        val totalText = "Total: " + newTotal + "€"
        totalView.text = totalText
    }

    override fun getItemCount() = products.size
}
