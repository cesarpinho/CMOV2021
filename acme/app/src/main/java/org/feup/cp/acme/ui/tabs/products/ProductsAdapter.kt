package org.feup.cp.acme.ui.tabs.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class ProductsAdapter(private val dataSet: List<HashMap<String, Any>>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    class ProductsViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        print(dataSet)
        return ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val card = holder.itemView
        card.findViewById<ImageView>(R.id.card_image)
            .setImageResource(dataSet[position]["card_image"] as Int)
        card.findViewById<TextView>(R.id.card_product_name).text =
            dataSet[position]["card_product_name"] as String
        card.findViewById<TextView>(R.id.card_product_price).text =
            dataSet[position]["card_product_price"] as String
        card.findViewById<Button>(R.id.card_button)
            .setOnClickListener(fun(view: View) {
                println("position: $position")
            })
    }

    override fun getItemCount() = dataSet.size
}