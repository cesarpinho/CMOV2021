package org.feup.cp.acme.ui.tabs.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class SnacksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hList = listOf(
            hashMapOf(
                "card_image" to R.drawable.ic_croissant,
                "card_product_name" to "Snack A",
                "card_product_price" to "0.5€"
            ),
            hashMapOf(
                "card_image" to R.drawable.ic_croissant,
                "card_product_name" to "Snack B",
                "card_product_price" to "1.25€"
            ),
            hashMapOf(
                "card_image" to R.drawable.ic_cart,
                "card_product_name" to "Snack C",
                "card_product_price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_snacks, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = ProductsAdapter(hList as List<HashMap<String, Any>>)
        listView.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SnacksFragment.
         */
        @JvmStatic
        fun newInstance() =
            SnacksFragment()
    }
}