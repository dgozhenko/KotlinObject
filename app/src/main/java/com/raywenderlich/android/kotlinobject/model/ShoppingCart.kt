package com.raywenderlich.android.kotlinobject.model

object ShoppingCart {

    // private setter, so only ShoppingCart can set it value
    var products: List<Product> = emptyList()
    private set

    // the only way to add products to list
    fun addProduct(product: Product) {
        products = products + listOf(product)
    }

    // clear list
    fun clear() {
        products = emptyList()
    }


}