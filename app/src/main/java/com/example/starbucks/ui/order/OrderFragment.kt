package com.example.starbucks.ui.order

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starbucks.R
import com.example.starbucks.databinding.FragmentOrderBinding
import com.example.starbucks.network.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderFragment : Fragment() {

    lateinit var binding: FragmentOrderBinding
    private val adapter = MenuAdapter()
    private val viewModel: OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)

        setRecyclerviewAdapter()
        getMenuList()
        updateMenu()
        listenButtonSelect()

        return binding.root
    }

    private fun listenButtonSelect() {
        binding.buttonMenuDrink.setOnClickListener {
            clearButtonGlowing()
            binding.buttonMenuDrink.setTypeface(binding.buttonMenuDrink.typeface, Typeface.BOLD)
            viewModel.drinkSelected()
        }
        binding.buttonMenuFood.setOnClickListener {
            clearButtonGlowing()
            binding.buttonMenuFood.setTypeface(binding.buttonMenuFood.typeface, Typeface.BOLD)
            viewModel.foodSelected()
        }
        binding.buttonMenuProduct.setOnClickListener {
            clearButtonGlowing()
            binding.buttonMenuProduct.setTypeface(binding.buttonMenuProduct.typeface, Typeface.BOLD)
            viewModel.productSelected()
        }
    }

    private fun clearButtonGlowing() {
        binding.buttonMenuFood.setTypeface(null, Typeface.NORMAL)
        binding.buttonMenuProduct.setTypeface(null, Typeface.NORMAL)
        binding.buttonMenuDrink.setTypeface(null, Typeface.NORMAL)
    }

    private fun setRecyclerviewAdapter() {
        binding.viewModel = viewModel
        binding.recyclerviewMenu.adapter = adapter
        binding.recyclerviewMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun getMenuList() {
        lifecycleScope.launch {
            viewModel.menuSelected.collect {
                viewModel.getMenu(it)
            }
        }
    }

    private fun updateMenu() {
        lifecycleScope.launch {
            viewModel.menuList.collect {
                if (it is NetworkResult.Success) {
                    adapter.submitList(it.data)
                }
            }
        }
    }

}