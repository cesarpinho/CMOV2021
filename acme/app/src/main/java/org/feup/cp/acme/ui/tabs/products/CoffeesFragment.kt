package org.feup.cp.acme.ui.tabs.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R

class CoffeesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hList = listOf(
            hashMapOf(
                "id_product" to "1",
                "card_image" to "https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg",
                "card_product_name" to "Coffee A",
                "card_product_price" to "0.5€"
            ),
            hashMapOf(
                "id_product" to "2",
                "card_image" to "https://pbs.twimg.com/profile_images/703891712427425793/KF1zgVqx.jpg",
                "card_product_name" to "Coffee B",
                "card_product_price" to "1.25€"
            ),
            hashMapOf(
                "id_product" to "3",
                "card_image" to "http://i.imgur.com/DvpvklR.png",
                "card_product_name" to "Coffee C",
                "card_product_price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_coffees, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = activity?.let { ProductsAdapter(hList, it) }
        listView.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CoffeesFragment()
    }
}







