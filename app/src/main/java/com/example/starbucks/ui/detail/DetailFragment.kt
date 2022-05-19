package com.example.starbucks.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.starbucks.R
import com.example.starbucks.databinding.FragmentDetail2Binding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetail2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail2, container, false)

        val args: DetailFragmentArgs by navArgs()
        binding.textviewDetailName.text = args.title
        return binding.root
    }

}


