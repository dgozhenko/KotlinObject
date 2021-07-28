package com.raywenderlich.android.kotlinobject.util

import com.raywenderlich.android.kotlinobject.model.ShoppingCart

class ShoppingCartCalculator {
    fun calculateTotal(): Int {
        val products = ShoppingCart.products
        var totalPrice = 0
        products.forEach{
            totalPrice += it.priceCents
        }
        return totalPrice
    }
}