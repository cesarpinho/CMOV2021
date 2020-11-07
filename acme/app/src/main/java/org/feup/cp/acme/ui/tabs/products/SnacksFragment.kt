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
                "id" to "7",
                "image" to "https://pbs.twimg.com/profile_images/2731599222/efcd6910b2b007029726ec62fda265cf.jpeg",
                "name" to "Snack A",
                "price" to "0.5€"
            ),
            hashMapOf(
                "id" to "8",
                "image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSs9LGR5IsTy2UQC5nxD87v-XvSDBNqy0LCBg&usqp=CAU",
                "name" to "Snack B",
                "price" to "1.25€"
            ),
            hashMapOf(
                "id" to "9",
                "image" to "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ5_0iP5YSQfydRwdXjkfLYC8HG5T0evJXG7Q&usqp=CAU",
                "name" to "Snack C",
                "price" to "5.75€"
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
        @JvmStatic
        fun newInstance() =
            SnacksFragment()
    }
}