package com.oscar.meli.ui.view.products.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oscar.meli.ui.model.ProductPlainVm

class ProductDiffUtil(private val newList: List<ProductPlainVm>,
                      private val oldList: List<ProductPlainVm>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            (oldList[oldItemPosition].name != newList[newItemPosition].name) -> false
            (oldList[oldItemPosition].id != newList[newItemPosition].id) -> false
            else -> {true}
        }
    }
}