package org.feup.cp.acme.ui.tabs.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.network.ReceiptInfoResponse

class ReceiptAdapter(private var dataSet: List<ReceiptInfoResponse>, val context: Context) :
    RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    /**
     * Receipt View holder class
     */
    class ReceiptViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    /**
     * Primary constructor
     */
    init {
        dataSet = dataSet.reversed()
    }

    /**
     * Creates card view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        return ReceiptViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_receipt_card, parent, false) as RelativeLayout
        )
    }

    /**
     * Binds a view holder / card with each receipt
     */
    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val card = holder.itemView

        val total = "Total: " + dataSet[position].total + " $"
        val productsList = card.findViewById<RecyclerView>(R.id.card_receipt_products_list)
        val productsAdapter =
            ReceiptProductsAdapter(dataSet[position].products)

        card.findViewById<TextView>(R.id.card_receipt_id).text =
            context.getString(R.string.str_hash_tag).plus(dataSet[position].code)
        card.findViewById<TextView>(R.id.card_receipt_date).text =
            dateFormat(dataSet[position].date.toString())
        card.findViewById<TextView>(R.id.card_receipt_total).text = total
        card.setOnClickListener(this::openCollapsible)

        productsList.layoutManager = LinearLayoutManager(context)
        productsList.adapter = productsAdapter
    }

    /**
     * Makes visible the receipt details
     */
    private fun openCollapsible(view: View) {
        view.findViewById<ImageView>(R.id.card_btn_collapse).rotationX = 180F
        view.findViewById<LinearLayout>(R.id.card_receipt_details).visibility =
            LinearLayout.VISIBLE
        view.setOnClickListener(this::closeCollapsible)
    }

    /**
     * Hides the receipt details
     */
    private fun closeCollapsible(view: View) {
        view.findViewById<ImageView>(R.id.card_btn_collapse).rotationX = 0F
        view.findViewById<LinearLayout>(R.id.card_receipt_details).visibility =
            LinearLayout.GONE
        view.setOnClickListener(this::openCollapsible)
    }

    /**
     * Returns the quantity of card views
     */
    override fun getItemCount() = dataSet.size


    /**
     * Returns a formatted string date
     */
    private fun dateFormat(date: String): String {
        val dateList = date.split(" ")
        return dateList[1] + " " + dateList[2] + ", " + dateList.last()
    }
}
