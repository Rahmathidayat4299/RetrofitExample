package com.example.retrofitexample.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrofitexample.model.Product
import com.example.retrofitexample.databinding.ItemProductListBinding

class ProductAdapter(private val onItemClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.CategoryViewHolder>() {
    private var items: MutableList<Product> = mutableListOf()
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.images == newItem.images
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitListCategory(item: List<Product>) {
        differ.submitList(item)
        Log.d("ProductAdapter", "Data masuk ke adapter: $item")
    }


    class CategoryViewHolder(
        val binding: ItemProductListBinding,
        onItemClick: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Product) {
            binding.tvProductimage.load(item.images[0])
            binding.tvProductname.text = item.title
            binding.tvProductprice.text = item.price.toString()
        }
    }
}