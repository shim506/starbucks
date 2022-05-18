package com.example.starbucks.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.starbucks.R
import com.example.starbucks.databinding.FragmentOrderBinding
import com.example.starbucks.network.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderFragment : Fragment() {

    lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)

        binding.viewModel = viewModel

        getMenuList()
        updateMenu()

        return binding.root
    }

    private fun updateMenu() {
        lifecycleScope.launch {
            viewModel.menuSelected.collect {
                viewModel.menuList.collect {
                    if (it is NetworkResult.Success) {
                        Log.d("order", it.data[0].title)
                    }
                }
            }
        }
    }

    private fun getMenuList() {
        lifecycleScope.launch {
            viewModel.menuSelected.collect {
                val x = viewModel.getMenu(it)
            }
        }
    }

}