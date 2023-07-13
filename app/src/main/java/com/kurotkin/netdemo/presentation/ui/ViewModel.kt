package com.kurotkin.netdemo.presentation.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kurotkin.netdemo.App
import com.kurotkin.netdemo.data.NetResource
import com.kurotkin.netdemo.data.RepoImpl
import com.kurotkin.netdemo.data.products.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
        get() = _products

    private val _loadingStatus= MutableLiveData<Boolean>()
    val loadingStatus : LiveData<Boolean>
        get() = _loadingStatus

    private val _errorStatus= MutableLiveData<String>()
    val errorStatus : LiveData<String>
        get() = _errorStatus


    fun download() {
        _loadingStatus.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val apiService = (app as App).apiService
            val result = RepoImpl(apiService).getAllProducts()
            when(result.status){
                NetResource.Status.SUCCESS -> {
                    _products.postValue(result.data?.products)
                }
                NetResource.Status.ERROR -> {
                    _errorStatus.postValue(result.message)
                }
                else -> {}
            }
            _loadingStatus.postValue(false)
        }
    }

}