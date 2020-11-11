package org.feup.cp.acme.ui.tabs.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.room.entity.Voucher

class VoucherAdapter(private var vouchers: List<Voucher>, val context: Context) :
    RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    /**
     * Voucher View holder class
     */
    class VoucherViewHolder(relativeLayout: RelativeLayout) :
        RecyclerView.ViewHolder(relativeLayout)

    /**
     * Primary constructor
     */
    init {
        vouchers = vouchers.reversed()
    }

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
            context.getString(R.string.str_hash_tag).plus(vouchers[position].code)
        date.text = dateFormat(vouchers[position].date.toString())
        date.visibility = TextView.VISIBLE

        // if true the voucher is a Discount voucher, otherwise is a coffee free voucher
        if (vouchers[position].type) {
            // Discount Voucher
            card.findViewById<ImageView>(R.id.card_image)
                .setImageResource(R.drawable.ic_discount)
            card.findViewById<TextView>(R.id.card_title).text =
                context.getText(R.string.str_discount_description)
        } else {
            // Coffee Voucher
            card.findViewById<ImageView>(R.id.card_image)
                .setImageResource(R.drawable.ic_free_coffee)
            card.findViewById<TextView>(R.id.card_title).text =
                context.getString(R.string.str_free_coffee)
        }
    }

    /**
     * Returns the quantity of card views
     */
    override fun getItemCount() = vouchers.size

    /**
     * Returns a formatted string date
     */
    private fun dateFormat(date: String): String {
        val dateList = date.split(" ")
        return dateList[1] + " " + dateList[2] + ", " + dateList.last()
    }
}
