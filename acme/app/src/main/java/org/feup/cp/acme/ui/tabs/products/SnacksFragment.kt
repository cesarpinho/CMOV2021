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
                "id_product" to "7",
                "card_image" to "https://pbs.twimg.com/profile_images/2731599222/efcd6910b2b007029726ec62fda265cf.jpeg",
                "card_product_name" to "Snack A",
                "card_product_price" to "0.5€"
            ),
            hashMapOf(
                "id_product" to "8",
                "card_image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSs9LGR5IsTy2UQC5nxD87v-XvSDBNqy0LCBg&usqp=CAU",
                "card_product_name" to "Snack B",
                "card_product_price" to "1.25€"
            ),
            hashMapOf(
                "id_product" to "9",
                "card_image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ5_0iP5YSQfydRwdXjkfLYC8HG5T0evJXG7Q&usqp=CAU",
                "card_product_name" to "Snack C",
                "card_product_price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_snacks, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = activity?.let { ProductsAdapter(hList, it) }
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