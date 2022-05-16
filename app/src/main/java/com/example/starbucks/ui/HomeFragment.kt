package com.example.starbucks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starbucks.R
import com.example.starbucks.data.model.Product
import com.example.starbucks.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val adapter = ProductAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setRecyclerviewAdapter()
        setRecyclerviewDummyData()

        return binding.root
    }

    private fun setRecyclerviewDummyData() {
        adapter.submitList(
            listOf(
                Product(
                    "이름1",
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002487]_20210426091745609.jpg"
                ),
                Product(
                    "이름2",
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002487]_20210426091745609.jpg"
                ),
                Product(
                    "이름2",
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002487]_20210426091745609.jpg"
                ),
                Product(
                    "이름3",
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002487]_20210426091745609.jpg"
                )
            )
        )
    }

    private fun setRecyclerviewAdapter() {
        binding.recyclerviewRecommend.adapter = adapter
        binding.recyclerviewRecommend.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }


}