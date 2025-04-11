package com.oscar.meli.ui.view.products.adapter

import androidx.recyclerview.widget.RecyclerView
import com.oscar.meli.databinding.DetailProductBinding
import com.oscar.meli.ui.model.ProductPlainVm

class ProductVH(private val binding: DetailProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductPlainVm) {
            binding.apply {
                tvName.text = product.name
                tvId.text = product.id
                tvBrand.text = product.brand
                renderImage(product.url)

            }


    }
    private fun renderImage(url: String?){

    }
}