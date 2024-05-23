package com.dan.marketapp.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dan.marketapp.R
import com.dan.marketapp.activities.ShoppingActivity
import com.dan.marketapp.adapters.ColorsAdapter
import com.dan.marketapp.adapters.SizeAdapter
import com.dan.marketapp.adapters.ViewPager2Images
import com.dan.marketapp.databinding.FragmentProductDetailsBinding
import com.dan.marketapp.di.hideBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductDetailsFragment:Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding:FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }
    private val sizeAdapter by lazy { SizeAdapter() }
    private val colorAdapter by lazy { ColorsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNavigation()
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product  = args.product

        setupSizesRv()
        setupColorsRv()
        setupViewPager()

        binding.imageClose.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.apply {
            tvProductName.text = product.name
            tvPrice.text = "$ ${product.price}"
            tvDescription.text = product.description

            if(product.colors.isNullOrEmpty())
                tvProductColor.visibility = View.INVISIBLE
            if(product.sizes.isNullOrEmpty())
                tvProductSize.visibility = View.INVISIBLE

        }

        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let{
            colorAdapter.differ.submitList(it)
        }
        product.sizes?.let{
            sizeAdapter.differ.submitList(it)
        }
    }

    private fun setupViewPager() {
        binding.apply{
            viewPagerProductImages.adapter = viewPagerAdapter
        }
    }

    private fun setupColorsRv() {
        binding.rvColors.apply{
            adapter = colorAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupSizesRv() {
        binding.rvSizes.apply{
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}