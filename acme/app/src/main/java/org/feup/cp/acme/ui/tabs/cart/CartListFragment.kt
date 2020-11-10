package org.feup.cp.acme.ui.tabs.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.singleton.Cart
import org.feup.cp.acme.singleton.CartData
import org.feup.cp.acme.ui.CartActivity
import org.feup.cp.acme.vmodel.CartViewModel

class CartListFragment(private val cartActivity: CartActivity) : Fragment() {

    /**
     * Vouchers view model
     */
    private val viewModel = CartViewModel()

    /**
     * Creates card list tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_list, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        listView.adapter =
            CartProductsAdapter(CartData("", ArrayList()), view.findViewById(R.id.cart_total))

        // Add listener to the Next button
        view.findViewById<Button>(R.id.btn_next).setOnClickListener {
            if (!Cart.getInstance()!!.isEmpty())
                cartActivity.addVoucherTab()
            else
                Toast.makeText(context, "Cart is empty!", Toast.LENGTH_LONG).show()
        }

        return view
    }

    /**
     * Operations after the view being created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cart.observe(viewLifecycleOwner) { cartData ->
            val listView = view.findViewById<RecyclerView>(R.id.list_view)
            val adapter = CartProductsAdapter(cartData, view.findViewById(R.id.cart_total))
            listView.adapter = adapter
        }
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance(cartActivity: CartActivity) = CartListFragment(cartActivity)
    }

}
