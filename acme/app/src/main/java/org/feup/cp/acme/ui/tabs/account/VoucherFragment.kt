package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class VoucherFragment : Fragment() {

    /**
     * Creates voucher tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO - Remove inactive voucher view
        val vouchers = listOf(
            mapOf(
                "id" to "2020disc1014", "type" to "discount",
                "date" to "14/10/2020", "active" to "true"
            ),
            mapOf(
                "id" to "2020disc1014", "type" to "coffee",
                "date" to "14/10/2020", "active" to "true"
            ),
            mapOf(
                "id" to "2020disc1014", "type" to "discount",
                "date" to "14/10/2020", "active" to "false"
            )
        )

        val view = inflater.inflate(R.layout.fragment_voucher, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.voucher_list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = VoucherAdapter(vouchers, inflater.context)
        listView.adapter = adapter

        return view
    }

    /**
     * Static function
     */
    companion object {
        @JvmStatic
        fun newInstance() = VoucherFragment()
    }
}