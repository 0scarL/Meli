package com.oscar.meli.ui.view.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oscar.meli.databinding.DetailProductBinding
import com.oscar.meli.ui.model.ProductPlainVm

class ProductAdapter(var productList: List<ProductPlainVm>) : RecyclerView.Adapter<ProductVH>() {

    lateinit var binding: DetailProductBinding

    fun updateList(newList: List<ProductPlainVm>) {
        val productDiffUtil = ProductDiffUtil(newList =newList, oldList=productList)
        val diffResult = DiffUtil.calculateDiff(productDiffUtil)
        productList = newList
        diffResult.dispatchUpdatesTo(this)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        binding = DetailProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductVH(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.bind(productList[position])
    }
}