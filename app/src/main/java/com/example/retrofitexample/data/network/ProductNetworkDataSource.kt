package com.example.retrofitexample.data.network

import com.example.retrofitexample.model.ProductResponse

interface ProductNetworkDataSource {
    suspend fun getProducts(): ProductResponse
}

class ProductNetworkDataSourceImpl(
    private val service: ProductService
) : ProductNetworkDataSource {
    override suspend fun getProducts(): ProductResponse = service.getProducts()
}