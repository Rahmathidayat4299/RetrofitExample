package com.example.retrofitexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.model.ProductResponse
import com.example.retrofitexample.data.repository.ProductsRepository
import com.example.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductsRepository) : ViewModel() {
    private val _responseProduct = MutableLiveData<ResultWrapper<ProductResponse>>()
    val responseProduct: LiveData<ResultWrapper<ProductResponse>>
        get() = _responseProduct

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts().collect {
                _responseProduct.postValue(it)
            }
        }
    }
}