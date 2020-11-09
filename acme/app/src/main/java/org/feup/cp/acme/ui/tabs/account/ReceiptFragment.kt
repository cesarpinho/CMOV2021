package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.feup.cp.acme.R
import org.feup.cp.acme.repository.ReceiptsRepository
import org.feup.cp.acme.repository.VouchersRepository
import org.feup.cp.acme.vmodel.ReceiptsViewModel
import org.feup.cp.acme.vmodel.VouchersViewModel

class ReceiptFragment : Fragment() {

    /**
     * Vouchers view model
     */
    private val viewModel = ReceiptsViewModel(ReceiptsRepository())

    /**
     * Creates receipt tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.receipt_list_view)
        listView.layoutManager = LinearLayoutManager(inflater.context)

        return view
    }

    /**
     * Operations after the view being created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.receipts.observe(viewLifecycleOwner) { receipts ->
            val listView = view.findViewById<RecyclerView>(R.id.receipt_list_view)
            val adapter = ReceiptAdapter(receipts, requireContext())
            listView.adapter = adapter
        }
    }

    /**
     * Static functions
     */
    companion object {

        @JvmStatic
        fun newInstance() = ReceiptFragment()
    }
}