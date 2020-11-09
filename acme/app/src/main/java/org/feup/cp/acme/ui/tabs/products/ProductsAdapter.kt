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
import org.feup.cp.acme.room.entity.Product

class ProductsAdapter(
    private val dataSet: List<Product>,
    private val activity: FragmentActivity
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    /**
     * Products View holder class
     */
    class ProductsViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    /**
     * Creates card view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    /**
     * Binds a view holder / card with each element of the dataSet
     */
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val card = holder.itemView
        val button = card.findViewById<Button>(R.id.card_button)

        Picasso.get().load(dataSet[position].icon)
            .into(card.findViewById<ImageView>(R.id.card_image))
        card.findViewById<TextView>(R.id.card_title).text =
            dataSet[position].name as String
        card.findViewById<TextView>(R.id.card_subtitle).text =
            dataSet[position].price.toString().plus("$")
        button.visibility = Button.VISIBLE
        button.setOnClickListener { _ ->
            val dialog = AddCartDialog(dataSet[position].id.toInt())
            dialog.show(activity.supportFragmentManager, "addCart")
        }
    }

    /**
     * Returns the quantity of card views
     */
    override fun getItemCount() = dataSet.size
}