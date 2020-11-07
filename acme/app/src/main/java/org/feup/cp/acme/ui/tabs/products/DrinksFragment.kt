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

    /**
     * Creates the drinks tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hList = listOf(
            hashMapOf(
                "id" to "4",
                "image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBHJ0huLjA-K31IVui7sPVZj_nC4acZbfgWQ&usqp=CAU",
                "name" to "Drink A",
                "price" to "10.5€"
            ),
            hashMapOf(
                "id" to "5",
                "image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRYME2L4Hcncff5cQiafc8tz0h-H76PK-PE1w&usqp=CAU",
                "name" to "Drink B",
                "price" to "1.25€"
            ),
            hashMapOf(
                "id" to "6",
                "image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhS6WbFgcxW0Gs7fwuvOWLO0OsoiPpr6jRaw&usqp=CAU",
                "name" to "Drink C",
                "price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_drinks, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = activity?.let { ProductsAdapter(hList, it) }
        listView.adapter = adapter

        return view
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = DrinksFragment()
    }
}