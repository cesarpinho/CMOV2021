package org.feup.cp.acme.singleton

import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.feup.cp.acme.network.ProductCartInfo
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.security.KeyStoreManager
import java.security.MessageDigest

data class CartData(
    @SerializedName("uuid")
    var uuid: String,
    @SerializedName("products")
    var products: ArrayList<ProductCartInfo>,
    @SerializedName("total")
    var total: Double = 0.0,
    @SerializedName("voucherCode")
    var voucherCode: String? = null,
    @SerializedName("signature")
    var signature: String? = null,
)

class Cart(private val cartData: LiveData<CartData>) {

    /**
     *  Retrieves the product index in the products
     *  structure. If the product does not exist
     *  returns -1.
     */
    private fun getProductIndex(name: String): Int {
        for (i in this.cartData.value!!.products.indices) {
            if (this.cartData.value!!.products[i].name == name)
                return i
        }
        return -1
    }

    /**
     *  Inserts a new product into the products
     *  structure.
     */
    fun insertProduct(product: ProductCartInfo) {
        val productIndex = this.getProductIndex(product.name)

        if (productIndex == -1)
            this.cartData.value!!.products.add(product)
        else
            this.cartData.value!!.products[productIndex].quantity += product.quantity

        // Update total value
        this.updateTotal()
    }

    /**
     *  Deletes a product from the products
     *  structure.
     */
    fun removeProduct(productName: String) {
        val productIndex = this.getProductIndex(productName)

        if (productIndex != -1)
            this.cartData.value!!.products.removeAt(productIndex)

        // Update total value
        this.updateTotal()
    }

    /**
     * Updates the total cart value
     */
    fun updateProductQuantity(productName: String, quantity: Int) {
        val productIndex = this.getProductIndex(productName)

        if (productIndex != -1)
            this.cartData.value!!.products[productIndex].quantity = quantity

        // Update total value
        this.updateTotal()
    }

    /**
     * Updates the total cart value
     */
    private fun updateTotal() {
        var totalValue = 0.0

        this.cartData.value!!.products.forEach() {
            totalValue += it.quantity * AppDatabase.getInstance()!!.productDao().getPrice(it.name)
        }

        this.cartData.value!!.total = totalValue
    }

    /**
     * Updates the cart voucher code used
     */
    fun setVoucher(code: String) {
        this.cartData.value!!.voucherCode = code
    }

    /**
     * Encodes the cart data to string and generates a signature.
     * The signature is generated from the hash of the json object string
     * representation of the cartData data class.
     */
    fun generateCartString(): String {
        // Ensure that the certificate at this point is null
        this.cartData.value!!.signature = null

        val input = Gson().toJson(this.cartData.value!!).toString().toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(input)

        this.cartData.value!!.signature = KeyStoreManager.signData(
            encodeToString(bytes, DEFAULT),
            KeyStoreManager.getPrivateKey(User.getInstance()!!.currentUser.nickname)
        )

        println(Gson().toJson(this.cartData.value!!).toString())
        return Gson().toJson(this.cartData.value!!).toString()
    }

    /**
     * Return cart information structure
     */
    fun getCartData(): LiveData<CartData> {
        return this.cartData
    }

    /**
     * Check if the cart have products
     */
    fun isEmpty(): Boolean {
        return this.cartData.value!!.products.isEmpty()
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
                    instance = Cart(
                        MutableLiveData(
                            CartData(
                                User.getInstance()!!.currentUser.uuid,
                                ArrayList()
                            )
                        )
                    )
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