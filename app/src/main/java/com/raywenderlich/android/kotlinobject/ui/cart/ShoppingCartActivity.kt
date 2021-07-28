/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.kotlinobject.ui.cart

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.raywenderlich.android.kotlinobject.R
import com.raywenderlich.android.kotlinobject.asPriceString
import com.raywenderlich.android.kotlinobject.model.Product
import com.raywenderlich.android.kotlinobject.ui.ProductListAdapter
import com.raywenderlich.android.kotlinobject.databinding.ActivityShoppingCartBinding
import com.raywenderlich.android.kotlinobject.model.ShoppingCart
import com.raywenderlich.android.kotlinobject.util.ShoppingCartCalculator

class ShoppingCartActivity : AppCompatActivity() {
  lateinit var viewBinding: ActivityShoppingCartBinding

  private lateinit var products: List<Product>

  // create anon object for listener
  private var onCartChangedListener = object : ShoppingCart.OnCartChangedListener {
    override fun onCartChanged() {
      setupProducts()
      setupRecyclerView()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewBinding = ActivityShoppingCartBinding.inflate(LayoutInflater.from(this))
    setContentView(viewBinding.root)

    setup()
    setupClearCartButton()

    ShoppingCart.setOnCartChangedListener(onCartChangedListener)
  }

  private fun setup() {
    setupProducts()
    setupRecyclerView()
  }

  private fun setupProducts() {
    products = ShoppingCart.products

    val calculator = ShoppingCartCalculator()
    val totalPrice = calculator.calculateTotal()
    viewBinding.textTotalCartValue.text = getString(R.string.text_total_price, totalPrice.asPriceString)
  }

  private fun setupClearCartButton() {
    viewBinding.clearCartButton.setOnClickListener {
      ShoppingCart.clear()
    }
  }

  private fun setupRecyclerView() {
    val recyclerView = viewBinding.recyclerViewCartProducts
    val adapter = ProductListAdapter()
    adapter.submitList(products)
    recyclerView.adapter = adapter
  }

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, ShoppingCartActivity::class.java)
    }
  }
}