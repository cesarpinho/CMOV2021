package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.CartActivity

class CartListFragment(private val cartActivity: CartActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_list, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        val adapter = CardProductsAdapter(view.findViewById(R.id.cart_total))
        listView.adapter = adapter

        view.findViewById<Button>(R.id.btn_next).setOnClickListener {
            cartActivity.addVoucherTab()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(cartActivity: CartActivity) = CartListFragment(cartActivity)
    }

}
