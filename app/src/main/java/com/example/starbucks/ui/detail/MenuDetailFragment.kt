package com.example.starbucks.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starbucks.R
import com.example.starbucks.databinding.FragmentDetailBinding
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.ui.order.MenuAdapter
import com.example.starbucks.ui.order.OrderViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel: MenuDetailViewModel by viewModel()
    private val adapter = MenuAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val args: MenuDetailFragmentArgs by navArgs()
        binding.textviewCategory.text = args.categoryName

        binding.recyclerviewMenuDetail.adapter = adapter
        binding.recyclerviewMenuDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        viewModel.getMenuDetail(args.url)

        lifecycleScope.launch {
            viewModel.detailFlow.collect {
                if (it is NetworkResult.Success) {
                    adapter.submitList(it.data)
                }
            }
        }


        listenReturnToMenu()
        return binding.root
    }

    private fun listenReturnToMenu() {
        binding.imageButtonBack.setOnClickListener {
            val action = MenuDetailFragmentDirections.actionDetailFragmentToOrderFragment()
            findNavController().navigate(action)
        }
    }
}