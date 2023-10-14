package com.example.retrofitexample.data.repository

import com.example.retrofitexample.data.network.ProductNetworkDataSource
import com.example.retrofitexample.utils.ResultWrapper
import com.example.retrofitexample.model.ProductResponse
import com.example.retrofitexample.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<ResultWrapper<ProductResponse>>
}

class ProductsRepositoryImpl(
    private val productNetworkDataSource: ProductNetworkDataSource
) : ProductsRepository {

    override suspend fun getProducts(): Flow<ResultWrapper<ProductResponse>> {
        return proceedFlow {
            productNetworkDataSource.getProducts()
        }
    }

}