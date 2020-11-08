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
     * Creates voucher tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vouchers = listOf(
            mapOf(
                "id" to "2020disc1014", "type" to "discount",
                "date" to "14/10/2020", "active" to "true"
            ),
            mapOf(
                "id" to "2020disc1014", "type" to "coffee",
                "date" to "14/10/2020", "active" to "true"
            ),
            mapOf(
                "id" to "2020disc1014", "type" to "discount",
                "date" to "14/10/2020", "active" to "false"
            )
        )

        val view = inflater.inflate(R.layout.fragment_voucher, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.voucher_list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)

        return view
    }

    /**
     * Operations after the view being created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vouchers.observe(viewLifecycleOwner) { vouchers ->
            val listView = view.findViewById<RecyclerView>(R.id.voucher_list_view)
            val adapter = VoucherAdapter(vouchers, requireContext())
            listView.adapter = adapter
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