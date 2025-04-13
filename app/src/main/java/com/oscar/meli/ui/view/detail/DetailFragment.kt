package com.oscar.meli.ui.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.oscar.meli.R
import com.oscar.meli.databinding.FragmentDetailBinding
import com.oscar.meli.ui.model.detail.DetailPlainVm
import com.oscar.meli.ui.model.product.ProductPlainVm
import com.oscar.meli.ui.model.product.toProductPlainVm
import com.oscar.meli.ui.model.states.DetailUiState
import com.oscar.meli.ui.viewmodel.SharedViewModel
import com.oscar.meli.ui.viewmodel.detail.DetailViewModel
import com.oscar.meli.utils.constants.UiConstants.MJS_ERROR
import com.oscar.meli.utils.constants.UiConstants.MJS_FIELD_EMPTY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    lateinit var productDetail : DetailPlainVm

    companion object {
        fun getDetailFragment() = DetailFragment()
    }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserverSelected()
        setObserverGetDetail()
        setListener()

    }

    private fun setListener() {
        binding.buttonFav.setOnClickListener {v-> onClickFavorite() }
    }

    private fun onClickFavorite() {
        if(!this.productDetail.favorite) {
            this.productDetail.favorite = true
            saveProduct(convertDetailToProduct(productDetail))
            saveDetails(productDetail)
        }else{
            applyFavoriteColor()
        }
    }

    private fun applyFavoriteColor() {
        binding.buttonFav.setColorFilter(resources.getColor(R.color.yellow))
    }

    private fun saveProduct(product: ProductPlainVm) {
        viewModel.saveProduct(product)
    }

    private fun convertDetailToProduct(productDetail: DetailPlainVm) =
          productDetail.toProductPlainVm(isFavorite = true)


    private fun saveDetails(productDetail: DetailPlainVm) {
        viewModel.saveDetail(productDetail)
    }


    private fun setObserverGetDetail() {
        viewModel.uiStateDetail.observe(viewLifecycleOwner, Observer { detailState ->
            when (detailState) {
                is DetailUiState.Loading -> {
                    setOnLoading()
                }

                is DetailUiState.Success -> {
                    setOffLoading()
                    renderDetail(detailState.detail)
                    catchProductDetail(productDetail = detailState.detail)

                }

                is DetailUiState.Error -> {
                    setOffLoading()
                    showErrorMessage(detailState.message)

                }


            }
        })
    }

    private fun catchProductDetail(productDetail: DetailPlainVm) {
            this.productDetail = productDetail
    }


//   fun setObserverSelected(){
//       lifecycleScope.launchWhenStarted {
//           sharedViewModel.selectedProduct.collect { selectedProduct ->
//               selectedProduct?.let {
//                   getProductDetail(selectedProduct.id)
//                   Log.d("meli DetailFragment", "Producto seleccionado: ${selectedProduct.name}")
//               }
//           }
//       }
//        setObserverGetDetail()
//    }


    private fun setObserverSelected() {
        sharedViewModel.selectedProduct.observe(viewLifecycleOwner, Observer { selectedProduct ->
            selectedProduct?.let {
                getProductDetail(selectedProduct.id, selectedProduct.favorite)
                Log.d("meli DetailFragment", "selected: ${selectedProduct.name}")

            }

        })
    }


    private fun getProductDetail(id: String, isfavorite: Boolean) {
        viewModel.getProductDetail(id, isfavorite)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, MJS_ERROR + "$message", Toast.LENGTH_SHORT).show()
    }

    private fun setOnLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setOffLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun renderDetail(producto: DetailPlainVm) {
        renderImage(producto.url)
        binding.apply {
            tvProductName.text = producto.name
            tvProductDescription.text = producto.description ?: MJS_FIELD_EMPTY
            tvOtherAttributes.text = getRenderText(producto)
        }

    }

    private fun renderImage(url: String?) {
        Glide.with(binding.ivProductImage.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivProductImage)
    }


    private fun getRenderText(producto: DetailPlainVm) =
        """
    ID: ${producto.id}
    Catálogo: ${producto.idCatalogo ?: MJS_FIELD_EMPTY}
    Estado: ${producto.status ?: MJS_FIELD_EMPTY}
    Dominio: ${producto.idDomain ?: MJS_FIELD_EMPTY}
    Familia: ${producto.family ?: MJS_FIELD_EMPTY}
    Marca: ${producto.brand ?: MJS_FIELD_EMPTY}
    Favorito: ${if (producto.favorite) "Sí" else "No"}
    Permalink: ${producto.permalink}
""".trimIndent()


}