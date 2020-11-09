package org.feup.cp.acme.ui.tabs.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.repository.ProductsRepository
import org.feup.cp.acme.room.entity.Product
import org.feup.cp.acme.vmodel.ProductsViewModel

class SnacksFragment : Fragment() {

    /**
     * Products view model
     */
    private val viewModel = ProductsViewModel(ProductsRepository())

    /**
     * Creates the snacks tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_snacks, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        listView.adapter = ProductsAdapter(listOf(),requireActivity())
        return view
    }

    /**
     * Operations after the view being created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.products.observe(viewLifecycleOwner) { products ->
            val data: ArrayList<Product> = ArrayList()

            products.forEach() {
                if(it.type == "snack")
                    data.add(it)
            }

            val listView = view.findViewById<RecyclerView>(R.id.list_view)
            listView.adapter = ProductsAdapter(data, requireActivity())
        }
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = SnacksFragment()
    }
}