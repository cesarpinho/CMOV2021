package org.feup.cp.acme.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.feup.cp.acme.repository.VouchersRepository
import org.feup.cp.acme.room.entity.Voucher

class VouchersViewModel(vouchersRepository: VouchersRepository): ViewModel() {

    /**
     * Live data variable that contains all the vouchers information
     * and that can dynamically change.
     */
    val vouchers: LiveData<List<Voucher>> = vouchersRepository.getVouchers()
}