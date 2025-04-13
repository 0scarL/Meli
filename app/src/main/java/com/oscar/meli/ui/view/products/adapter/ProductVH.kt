package com.oscar.meli.ui.view.products.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oscar.meli.R
import com.oscar.meli.databinding.DetailProductBinding
import com.oscar.meli.ui.model.product.ProductPlainVm

class ProductVH(private val binding: DetailProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            product: ProductPlainVm,
            onClickFavorite: (ProductPlainVm) -> Unit,
            selectedProduct: (ProductPlainVm) -> Unit
        ) {
            binding.apply {
                tvName.text = product.name
                tvId.text = product.id
                tvBrand.text = product.brand
                renderImage(product.url)
                if (product.favorite) {buttonFav.visibility = View.VISIBLE}
                else buttonFav.visibility = View.GONE
            }
            deleteFavorite(product, onClickFavorite)
            listenerSelected(product, selectedProduct)



    }

    private fun listenerSelected(product: ProductPlainVm, selectedProduct: (ProductPlainVm) -> Unit) {
        binding.cardProduct.setOnClickListener{ selectedProduct(product)}
    }

    private fun deleteFavorite(product: ProductPlainVm,
                               onClickFavorite: (ProductPlainVm) -> Unit) {
        binding.buttonFav.setOnClickListener {
            onClickFavorite(product)
        }
    }


    private fun isFavorite(favorite: Boolean) {
        if (favorite) {
            binding.buttonFav.visibility = View.VISIBLE
            }
        else binding.buttonFav.visibility = View.GONE
        }


    private fun renderImage(url: String?){
        Glide.with(binding.imgProduct.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imgProduct)
    }
}