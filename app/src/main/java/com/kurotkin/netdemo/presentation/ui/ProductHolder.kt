package com.kurotkin.netdemo.presentation.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kurotkin.netdemo.R

class ProductHolder(val view: View) : RecyclerView.ViewHolder(view){
    var productId: TextView = itemView.findViewById(R.id.productId)
    val productName: TextView = itemView.findViewById(R.id.productName)
}