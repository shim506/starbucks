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

private const val VIEW_TYPE_DETAIL_MENU = 1
private const val VIEW_TYPE_CATEGORY_MENU = 2

class MenuAdapter(private val navigation: NavigationListener? = null) :
    ListAdapter<Menu, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: MenuItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.menu_item,
                parent,
                false
            )
        return if (viewType == VIEW_TYPE_DETAIL_MENU) MenuDetailViewHolder(binding)
        else MenuCategoryViewHolder(binding, navigation)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is MenuDetailViewHolder) {
            viewHolder.bind(getItem(position) as Menu.MenuDetail)
        } else if (viewHolder is MenuCategoryViewHolder) {
            viewHolder.bind(getItem(position) as Menu.MenuCategory)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Menu.MenuDetail -> VIEW_TYPE_DETAIL_MENU
            is Menu.MenuCategory -> VIEW_TYPE_CATEGORY_MENU
            else -> 0
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return when (oldItem) {
                    is Menu.MenuDetail -> oldItem.title == (newItem as? Menu.MenuDetail)?.title ?: false

                    is Menu.MenuCategory -> {
                        oldItem.title == (newItem as? Menu.MenuCategory)?.title ?: false
                    }
                }
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }
        }
    }


}


class MenuDetailViewHolder(
    val binding: MenuItemBinding, private val navigation: NavigationListener? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Menu.MenuDetail) {
        binding.menuItemTitle.text = item.title
        binding.imageviewMenuItem.load(item.image) {
            transformations(CircleCropTransformation())
        }
        binding.root.setOnClickListener {
            //navigation?.moveNavigation(item.url, item.title)
        }
    }
}

class MenuCategoryViewHolder(
    val binding: MenuItemBinding,
    private val navigation: NavigationListener? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Menu.MenuCategory) {
        binding.menuItemTitle.text = item.title
        binding.menuItemSubTitle.text = item.subTitle
        binding.imageviewMenuItem.load(item.image) {
            transformations(CircleCropTransformation())
        }
        binding.root.setOnClickListener {
            navigation?.moveNavigation(item.url, item.title)
        }

    }
}