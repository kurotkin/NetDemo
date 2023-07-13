package com.kurotkin.netdemo.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kurotkin.netdemo.R
import com.kurotkin.netdemo.data.products.Product

class ProductAdapter : ListAdapter<Product, ProductHolder>(ProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layout = R.layout.layout
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = getItem(position)
        holder.productId.text = item.id.toString()
        holder.productName.text = item.title
    }
}