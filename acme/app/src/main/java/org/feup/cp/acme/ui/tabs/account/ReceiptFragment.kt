package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class ReceiptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val receipts = listOf(
            mapOf(
                "id" to "12349875", "date" to "14/10/2020", "total" to "1,50€",
                "products" to listOf(
                    mapOf(
                        "image" to "https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg",
                        "name" to "Coffee A",
                        "price" to "0,50€",
                        "quantity" to "2"
                    ), mapOf(
                        "image" to "https://pbs.twimg.com/profile_images/703891712427425793/KF1zgVqx.jpg",
                        "name" to "Coffee B",
                        "price" to "0,50€",
                        "quantity" to "1"
                    )
                )
            ),
            mapOf(
                "id" to "12983476", "date" to "03/11/2020", "total" to "1,00€",
                "products" to listOf(
                    mapOf(
                        "image" to "https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg",
                        "name" to "Coffee A",
                        "price" to "0,50€",
                        "quantity" to "1"
                    )
                )
            )
        )
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.receipt_list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = ReceiptAdapter(receipts, inflater.context)
        listView.adapter = adapter

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = ReceiptFragment()
    }
}