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

package com.raywenderlich.android.kotlinobject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.kotlinobject.asPriceString
import com.raywenderlich.android.kotlinobject.databinding.RecyclerItemProductBinding
import com.raywenderlich.android.kotlinobject.model.Product

class ProductListAdapter(
    private val onItemClick: ((position: Int) -> Unit)? = null
): ListAdapter<Product, ProductListAdapter.ProductViewHolder>(DIFF_CALLBACK) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
    return ProductViewHolder(
        RecyclerItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )
  }

  override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
    val product = getItem(position)
    holder.bindTo(product)
  }

  class ProductViewHolder(
      @NonNull binding: RecyclerItemProductBinding,
      onItemClick: ((position: Int) -> Unit)?
  ): RecyclerView.ViewHolder(binding.root) {
    val imageProductThumbnail: ImageView = binding.imageProductThumbnail
    val textProductName: TextView = binding.textProductName
    val textProductPrice: TextView = binding.textProductPrice

    init {
      itemView.setOnClickListener {
        onItemClick?.invoke(bindingAdapterPosition)
      }
    }

    fun bindTo(product: Product) {
      imageProductThumbnail.setImageResource(product.imageResource)
      textProductName.text = product.name
      textProductPrice.text = product.priceCents.asPriceString
    }
  }

  private companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
      override fun areItemsTheSame(oldItem: Product, newItem: Product) =
          oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: Product, newItem: Product) =
          oldItem == newItem
    }
  }
}