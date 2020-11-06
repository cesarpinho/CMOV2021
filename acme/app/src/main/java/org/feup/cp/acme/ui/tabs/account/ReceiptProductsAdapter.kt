package org.feup.cp.acme.ui.tabs.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.feup.cp.acme.R

class ReceiptProductsAdapter(val products: List<Map<String, String>>) :
    RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiptAdapter.ReceiptViewHolder {
        return ReceiptAdapter.ReceiptViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    override fun onBindViewHolder(holder: ReceiptAdapter.ReceiptViewHolder, position: Int) {
        val card = holder.itemView
        card.findViewById<View>(R.id.divider).visibility = View.GONE
        card.findViewById<TextView>(R.id.card_title).text = products[position]["name"]
        card.findViewById<TextView>(R.id.card_subtitle).text = products[position]["price"]
        val quantity = card.findViewById<TextView>(R.id.card_right_info)
        quantity.text = products[position]["quantity"].plus(" units")
        quantity.textSize = 25F
        Picasso.get().load(products[position]["image"])
            .into(card.findViewById<ImageView>(R.id.card_image))
    }

    override fun getItemCount() = products.size

}