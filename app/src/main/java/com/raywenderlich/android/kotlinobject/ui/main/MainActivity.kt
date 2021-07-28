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

package com.raywenderlich.android.kotlinobject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.kotlinobject.model.Product
import com.raywenderlich.android.kotlinobject.ui.ProductListAdapter
import com.raywenderlich.android.kotlinobject.R
import com.raywenderlich.android.kotlinobject.databinding.ActivityMainBinding

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {
  private lateinit var viewBinding: ActivityMainBinding

  private lateinit var products: List<Product>

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)

    viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
    setContentView(viewBinding.root)

    setup()
  }

  private fun addProductToCart(product: Product) {
    // TODO
  }

  private fun goToCart() {
    // TODO
  }

  private fun setup() {
    setupProducts()
    setupRecyclerView()
    setupGoToCartButton()
  }


  private fun setupProducts() {
    products = listOf(
        Product(1, R.mipmap.android_sailor, getString(R.string.product_sailor), 1499),
        Product(2, R.mipmap.android_basketball, getString(R.string.product_basketball_shirt), 1199),
        Product(3, R.mipmap.android_design_pattern, getString(R.string.product_design_pattern),
            899),
        Product(4, R.mipmap.android_jacket, getString(R.string.product_jacket), 4299),
    )
  }

  private fun setupRecyclerView() {
    val recyclerView = viewBinding.recyclerViewProducts
    val adapter = ProductListAdapter { position ->
      addProductToCart(products[position])
    }
    adapter.submitList(products)
    recyclerView.adapter = adapter
  }

  private fun setupGoToCartButton() {
    viewBinding.cartButton.setOnClickListener { goToCart() }
  }
}
