package com.example.starbucks.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.starbucks.R
import com.example.starbucks.databinding.FragmentDetailBinding

class MenuDetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val args: MenuDetailFragmentArgs by navArgs()
        binding.textviewCategory.text = args.categoryName


        returnToMenu()





        return binding.root
    }

    private fun returnToMenu() {
        binding.imageButtonBack.setOnClickListener {
            val action = MenuDetailFragmentDirections.actionDetailFragmentToOrderFragment()
            findNavController().navigate(action)
        }
    }


}