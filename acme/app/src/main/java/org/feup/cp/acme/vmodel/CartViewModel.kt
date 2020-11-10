package org.feup.cp.acme.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.feup.cp.acme.singleton.Cart
import org.feup.cp.acme.singleton.CartData

class CartViewModel : ViewModel() {

    /**
     * Live data variable that contains all the cart information
     * and that can dynamically change.
     */
    val cart: LiveData<CartData> = Cart.getInstance()!!.getCartData()
}