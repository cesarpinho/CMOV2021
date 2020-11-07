package org.feup.cp.acme.ui.tabs.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class VoucherAdapter(private val vouchers: List<Map<String, String>>, val context: Context) :
    RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    /**
     * Voucher View holder class
     */
    class VoucherViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    /**
     * Creates card view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        return VoucherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_card, parent, false) as RelativeLayout
        )
    }

    /**
     * Binds a view holder / card with each voucher
     */
    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val card = holder.itemView
        val date = card.findViewById<TextView>(R.id.card_right_info)
        card.findViewById<TextView>(R.id.card_subtitle).text =
            context.getString(R.string.str_hash_tag).plus(vouchers[position]["id"])
        date.text = vouchers[position]["date"]
        date.visibility = TextView.VISIBLE

        when (vouchers[position]["type"]) {
            "discount" -> {
                card.findViewById<ImageView>(R.id.card_image)
                    .setImageResource(R.drawable.ic_discount)
                card.findViewById<TextView>(R.id.card_title).text =
                    context.getText(R.string.str_discount_description)
            }
            "coffee" -> {
                card.findViewById<ImageView>(R.id.card_image)
                    .setImageResource(R.drawable.ic_free_coffee)
                card.findViewById<TextView>(R.id.card_title).text = context
                    .getString(R.string.str_free_coffee)
            }
        }

        if (!vouchers[position]["active"].toBoolean())
            card.findViewById<View>(R.id.card_blocker).visibility = View.VISIBLE

    }

    /**
     * Returns the quantity of card views
     */
    override fun getItemCount() = vouchers.size
}
