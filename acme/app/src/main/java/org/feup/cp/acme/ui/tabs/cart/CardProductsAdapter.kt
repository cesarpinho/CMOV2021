package org.feup.cp.acme.ui.tabs.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.feup.cp.acme.R

class CardProductsAdapter() :
    RecyclerView.Adapter<CardProductsAdapter.ViewHolder>() {
    class ViewHolder(relativeLayout: RelativeLayout) : RecyclerView.ViewHolder(relativeLayout)

    //    TODO("Replace with Cart info")
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
        removeBtn.visibility = Button.VISIBLE
        removeBtn.setOnClickListener { view ->
            products.removeAt(position)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount() = products.size
}
