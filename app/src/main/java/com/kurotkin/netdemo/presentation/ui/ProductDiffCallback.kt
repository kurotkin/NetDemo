package com.kurotkin.netdemo.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.kurotkin.netdemo.data.products.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}