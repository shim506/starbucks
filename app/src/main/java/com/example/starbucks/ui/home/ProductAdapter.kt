package com.example.starbucks.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.starbucks.R
import com.example.starbucks.data.model.Product
import com.example.starbucks.databinding.ProductItemBinding

class ProductAdapter : ListAdapter<Product, ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class ViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.textviewProductName.text = product.name
        binding.imageviewProductRound.load(product.image) {
            transformations(CircleCropTransformation())
        }
        product.index?.let {
            binding.textviewProductIndex.text = it.toString()
        }

    }
}
