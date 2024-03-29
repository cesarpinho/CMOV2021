package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.repository.ProductsRepository
import org.feup.cp.acme.repository.VouchersRepository
import org.feup.cp.acme.room.entity.Product
import org.feup.cp.acme.ui.tabs.products.ProductsAdapter
import org.feup.cp.acme.vmodel.ProductsViewModel
import org.feup.cp.acme.vmodel.VouchersViewModel

class VoucherFragment : Fragment() {

    /**
     * Vouchers view model
     */
    private val viewModel = VouchersViewModel(VouchersRepository())

    /**
     * Voucher Adapter instance
     */
    private lateinit var adapter: VoucherAdapter

    /**
     * Creates voucher tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voucher, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.voucher_list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)
        adapter = VoucherAdapter(listOf(), requireContext())
        listView.adapter = adapter

        return view
    }

    /**
     * Operations after the view being created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vouchers.observe(viewLifecycleOwner) { vouchers ->
            adapter.setData(vouchers)
        }
    }

    /**
     * Static function
     */
    companion object {
        @JvmStatic
        fun newInstance() = VoucherFragment()
    }
}