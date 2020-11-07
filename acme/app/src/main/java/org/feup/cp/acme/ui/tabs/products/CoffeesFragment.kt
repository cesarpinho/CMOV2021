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

    /**
     * Creates the coffees tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hList = listOf(
            hashMapOf(
                "id" to "1",
                "image" to "https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg",
                "name" to "Coffee A",
                "price" to "0.5€"
            ),
            hashMapOf(
                "id" to "2",
                "image" to "https://pbs.twimg.com/profile_images/703891712427425793/KF1zgVqx.jpg",
                "name" to "Coffee B",
                "price" to "1.25€"
            ),
            hashMapOf(
                "id" to "3",
                "image" to "http://i.imgur.com/DvpvklR.png",
                "name" to "Coffee C",
                "price" to "5.75€"
            )
        )

        val view = inflater.inflate(R.layout.fragment_coffees, container, false)
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
        fun newInstance() = CoffeesFragment()
    }
}







