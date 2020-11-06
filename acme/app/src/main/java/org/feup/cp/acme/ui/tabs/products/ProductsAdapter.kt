package org.feup.cp.acme.ui.tabs.products

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.feup.cp.acme.R

class ProductsAdapter(
    private val dataSet: List<HashMap<String, String>>,
    private val activity: FragmentActivity
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    class ProductsViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        return ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val card = holder.itemView
        val button = card.findViewById<Button>(R.id.card_button)

        Picasso.get().load(dataSet[position]["card_image"])
            .into(card.findViewById<ImageView>(R.id.card_image))
        card.findViewById<TextView>(R.id.card_title).text =
            dataSet[position]["card_product_name"] as String
        card.findViewById<TextView>(R.id.card_subtitle).text =
            dataSet[position]["card_product_price"] as String
        button.visibility = Button.VISIBLE
        button.setOnClickListener { view ->
            val dialog = AddCartDialog(dataSet[position]["id_product"]!!.toInt())
            dialog.show(activity.supportFragmentManager, "addCart")
        }
    }

    override fun getItemCount() = dataSet.size
}