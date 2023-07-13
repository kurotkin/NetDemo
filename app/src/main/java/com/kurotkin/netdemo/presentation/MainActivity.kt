package com.kurotkin.netdemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kurotkin.netdemo.R
import com.kurotkin.netdemo.presentation.ui.ProductAdapter
import com.kurotkin.netdemo.presentation.ui.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel
    private lateinit var rvList: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Note1().primitive()

        // TODO: Вариант лучше
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        viewModel.download()
        initViews()
        initObserves()
    }



    private fun initViews(){
        rvList = findViewById(R.id.list)
        productAdapter = ProductAdapter()
        rvList.adapter = productAdapter
    }

    private fun initObserves(){
        viewModel.products.observe(this){
            productAdapter.submitList(it)
        }
    }
}