package org.feup.cp.acme.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.feup.cp.acme.network.ReceiptInfoResponse
import org.feup.cp.acme.repository.ReceiptsRepository

class ReceiptsViewModel(receiptsRepository: ReceiptsRepository): ViewModel() {

    /**
     * Live data variable that contains all the receipts information
     * and that can dynamically change.
     */
    val receipts: LiveData<List<ReceiptInfoResponse>> = receiptsRepository.getReceipts()
}