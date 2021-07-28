package com.raywenderlich.android.kotlinobject.model

import java.lang.ref.WeakReference

object ShoppingCart {

    // private setter, so only ShoppingCart can set it value
    @JvmStatic
    var products: List<Product> = emptyList()
    private set

    // create listener that will notify whenever data changes
    private var onCartChangedListener: WeakReference<OnCartChangedListener>? = null

    fun setOnCartChangedListener(listener: OnCartChangedListener) {
        this.onCartChangedListener = WeakReference(listener)
    }

    private fun notifyCartChanged() {
        onCartChangedListener?.get()?.onCartChanged()
    }

    // the only way to add products to list
    fun addProduct(product: Product) {
        products = products + listOf(product)
        notifyCartChanged()
    }

    // clear list
    fun clear() {
        products = emptyList()
        notifyCartChanged()
    }

    interface OnCartChangedListener{
        fun onCartChanged()
    }
}