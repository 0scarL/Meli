package com.oscar.meli.ui.view.products.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oscar.meli.ui.model.product.ProductPlainVm

/**
 * Implementación de `DiffUtil.Callback` que compara dos listas de objetos `ProductPlainVm`
 * para determinar las diferencias entre ellas. Esta clase es útil para actualizar eficientemente
 * una lista en un `RecyclerView` en función de las diferencias entre la lista nueva y la lista antigua.
 *
 * @property newList Lista de productos nuevos que se va a comparar.
 * @property oldList Lista de productos antiguos con la que se va a comparar la lista nueva.
 */
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
            (oldList[oldItemPosition].favorite != newList[newItemPosition].favorite) -> false
            else -> {true}
        }
    }
}