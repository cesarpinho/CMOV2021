package org.feup.cp.acme.singleton

import com.google.gson.annotations.SerializedName
import org.feup.cp.acme.network.ProductQuantityInfo

class Cart(val cartData: CartData) {

    /**
     *  Retrieves the product index in the products
     *  structure. If the product does not exist
     *  returns -1.
     */
    private fun getProductIndex(name: String): Int {
        for(i in this.cartData.products.indices) {
            if(this.cartData.products[i].name == name)
                return i
        }
        return -1
    }

    /**
     *  Inserts a new product into the products
     *  structure.
     */
    fun insertProduct(product: ProductQuantityInfo) {
        val productIndex = this.getProductIndex(product.name)

        if(productIndex != -1)
            this.cartData.products.add(product)
    }

    /**
     *  Deletes a product from the products
     *  structure.
     */
    fun removeProduct(productName: String) {
        val productIndex = this.getProductIndex(productName)

        if(productIndex != -1)
            this.cartData.products.removeAt(productIndex)
    }

    /**
     * Static functions
     */
    companion object {

        /**
         * Current user instance
         */
        var instance: Cart? = null

        /**
         * Returns current user instance
         */
        @JvmName("getCartInstance")
        fun getInstance(): Cart? {
            if (instance == null) {
                synchronized(lock = true) {
                    instance = Cart(CartData(User.getInstance()!!.currentUser.uuid, ArrayList()))
                }
            }
            return instance
        }

        /**
         * Destroys database instance
         */
        fun destroyCart() {
            instance = null
        }
    }

}

data class CartData(
        @SerializedName("uuid")
        var uuid : String,
        @SerializedName("products")
        var products : ArrayList<ProductQuantityInfo>,
        @SerializedName("voucherCode")
        var voucherCode : String? = null,
        @SerializedName("signature")
        var signature : String? = null,
)