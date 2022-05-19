package com.example.starbucks.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.starbucks.R
import com.example.starbucks.data.model.Menu
import com.example.starbucks.databinding.MenuItemBinding


class MenuAdapter : ListAdapter<Menu, MenuViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding: MenuItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.menu_item,
                parent,
                false
            )
        return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class MenuViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Menu) {
        binding.menuItemTitle.text = item.title
        binding.menuItemSubTitle.text = item.subTitle
        binding.imageviewMenuItem.load(item.image) {
            transformations(CircleCropTransformation())
        }

    }
}