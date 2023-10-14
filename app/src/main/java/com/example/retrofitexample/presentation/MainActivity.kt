package com.example.retrofitexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitexample.data.network.ProductNetworkDataSource
import com.example.retrofitexample.data.network.ProductNetworkDataSourceImpl
import com.example.retrofitexample.data.repository.ProductsRepository
import com.example.retrofitexample.data.repository.ProductsRepositoryImpl
import com.example.retrofitexample.data.network.ProductService
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.utils.GenericViewModelFactory
import com.example.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        val service: ProductService by lazy {
            ProductService.invoke()
        }
        val nds: ProductNetworkDataSource = ProductNetworkDataSourceImpl(service)
        val repo: ProductsRepository = ProductsRepositoryImpl(nds)
        GenericViewModelFactory.create(MainViewModel(repo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        val recyclerView = binding.productRcv
        viewModel.getProducts()
        recyclerView.layoutManager =
            LinearLayoutManager(this)
        val adapter = ProductAdapter(
            onItemClick = { category ->
            },

            )
        recyclerView.adapter = adapter
        viewModel.responseProduct.observe(this) { product ->
            product.proceedWhen(
                doOnSuccess = {
                    binding.loadingIcon.isVisible = false
                    binding.productRcv.isVisible = true
                    val productList = product.payload?.products ?: emptyList()
                    adapter.submitListCategory(productList)
                },
                doOnError = {
                    Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    binding.loadingIcon.isVisible = true
                    binding.productRcv.isVisible = false
                }
            )
        }

    }


}