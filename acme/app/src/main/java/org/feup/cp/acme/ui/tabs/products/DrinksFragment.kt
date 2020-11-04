package org.feup.cp.acme.ui.tabs.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class DrinksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hList = listOf(
            hashMapOf(
                "card_image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBHJ0huLjA-K31IVui7sPVZj_nC4acZbfgWQ&usqp=CAU",
                "card_product_name" to "Drink A",
                "card_product_price" to "10.5€"
            ),
            hashMapOf(
                "card_image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRYME2L4Hcncff5cQiafc8tz0h-H76PK-PE1w&usqp=CAU",
                "card_product_name" to "Drink B",
                "card_product_price" to "1.25€"
            ),
            hashMapOf(
                "card_image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhS6WbFgcxW0Gs7fwuvOWLO0OsoiPpr6jRaw&usqp=CAU",
                "card_product_name" to "Drink C",
                "card_product_price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_drinks, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = ProductsAdapter(hList)
        listView.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DrinksFragment.
         */
        @JvmStatic
        fun newInstance() =
            DrinksFragment()
    }
}