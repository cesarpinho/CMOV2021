package org.feup.cp.acme.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.feup.cp.acme.repository.ProductsRepository
import org.feup.cp.acme.room.entity.Product

class ProductsViewModel(productsRepository: ProductsRepository): ViewModel() {

    /**
     * Live data variable that contains all the products information
     * and that can dynamically change.
     */
    val products: LiveData<List<Product>> = productsRepository.getProducts()
}