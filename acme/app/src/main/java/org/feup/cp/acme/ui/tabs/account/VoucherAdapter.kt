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

    class VoucherViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        return VoucherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_voucher_card, parent, false) as RelativeLayout
        )
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val card = holder.itemView
        card.findViewById<TextView>(R.id.card_voucher_id).text =
            context.getString(R.string.str_hash_tag).plus(vouchers[position]["id"])
        card.findViewById<TextView>(R.id.card_voucher_date).text = vouchers[position]["date"]

        when (vouchers[position]["type"]) {
            "discount" -> {
                card.findViewById<ImageView>(R.id.card_image)
                    .setImageResource(R.drawable.ic_discount)
                card.findViewById<TextView>(R.id.card_voucher_description).text =
                    context.getText(R.string.str_discount_description)
            }
            "coffee" -> {
                card.findViewById<ImageView>(R.id.card_image)
                    .setImageResource(R.drawable.ic_free_coffee)
                card.findViewById<TextView>(R.id.card_voucher_description).text = context
                    .getString(R.string.str_free_coffee)
            }
        }

        if (!vouchers[position]["active"].toBoolean())
            card.findViewById<View>(R.id.card_voucher_blocker).visibility = View.VISIBLE

    }

    override fun getItemCount() = vouchers.size
}
