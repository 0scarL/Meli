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
import com.oscar.meli.utils.constants.UiConstants.LABEL_BRAND
import com.oscar.meli.utils.constants.UiConstants.LABEL_CATALOG
import com.oscar.meli.utils.constants.UiConstants.LABEL_DOMAIN
import com.oscar.meli.utils.constants.UiConstants.LABEL_FAMILY
import com.oscar.meli.utils.constants.UiConstants.LABEL_FAVORITE
import com.oscar.meli.utils.constants.UiConstants.LABEL_ID
import com.oscar.meli.utils.constants.UiConstants.LABEL_SITE
import com.oscar.meli.utils.constants.UiConstants.LABEL_STATUS
import com.oscar.meli.utils.constants.UiConstants.MELI_FRAGMENT
import com.oscar.meli.utils.constants.UiConstants.MJS_ERROR
import com.oscar.meli.utils.constants.UiConstants.MJS_FIELD_EMPTY
import com.oscar.meli.utils.constants.UiConstants.TAG_SELECTEED_PRODUCT
import com.oscar.meli.utils.constants.UiConstants.TEXT_NO
import com.oscar.meli.utils.constants.UiConstants.TEXT_YES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    lateinit var productDetail: DetailPlainVm
    var isFavorite = false

    companion object {
        fun getDetailFragmentInstance() = DetailFragment()
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

    private fun setVisibility(detail: DetailPlainVm) {
        if (detail.favorite) {
            binding.btnFav.setImageResource(R.drawable.ic_favorite)
            isFavorite = true
        } else {
            binding.btnFav.setImageResource(R.drawable.ic_favorite_border)
            isFavorite = false
        }
    }


    private fun setListener() {
        binding.btnFav.setOnClickListener {
            isFavorite = !isFavorite
            binding.btnFav.setImageResource(
                if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )
            if (isFavorite && this.productDetail.id != null) {
                saveFavorite()
            } else {
                removeFavorite()
            }
        }


    }

    private fun saveFavorite() {
        productDetail.favorite = true
        saveProduct(convertDetailToProduct(productDetail))
        saveDetails(productDetail)
    }

    private fun removeFavorite() {
        productDetail.favorite = false
        deleteFavorite(productDetail.id)
    }


    private fun deleteFavorite(id: String) {
        viewModel.deleteProduct(id)
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
            var isFavorite = false
            when (detailState) {
                is DetailUiState.Loading -> {
                    setOnLoading()
                }

                is DetailUiState.Success -> {
                    setOffLoading()
                    renderDetail(detailState.detail)
                    catchProductDetail(productDetail = detailState.detail)
                    isFavorite = detailState.detail.favorite
                    setVisibility(detailState.detail)

                }

                is DetailUiState.Error -> {
                    setOffLoading()
                    showErrorMessage(detailState.message)
                    if (!isFavorite)
                        renderNotAvailable()

                }


            }
        })
    }

    private fun renderNotAvailable() {
        binding.apply {
            includeCardError.idCardError.visibility = View.VISIBLE
            tvProductName.visibility = View.GONE
            tvProductDescription.visibility = View.GONE
            tvOtherAttributes.visibility = View.GONE
            ivProductImage.visibility = View.GONE
            btnFav.visibility = View.GONE

        }

    }

    private fun catchProductDetail(productDetail: DetailPlainVm) {
        this.productDetail = productDetail
    }


    private fun setObserverSelected() {
        sharedViewModel.selectedProduct.observe(viewLifecycleOwner, Observer { selectedProduct ->
            selectedProduct?.let {
                getProductDetail(selectedProduct.id, selectedProduct.favorite)
                Log.d(MELI_FRAGMENT, TAG_SELECTEED_PRODUCT +selectedProduct.name )

            }

        })
    }


    private fun getProductDetail(id: String, isfavorite: Boolean) {
        viewModel.getProductDetail(id, isfavorite)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, MJS_ERROR + message, Toast.LENGTH_SHORT).show()
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
        $LABEL_ID: ${producto.id}
        $LABEL_CATALOG: ${producto.idCatalogo ?: MJS_FIELD_EMPTY}
        $LABEL_STATUS: ${producto.status ?: MJS_FIELD_EMPTY}
        $LABEL_DOMAIN: ${producto.idDomain ?: MJS_FIELD_EMPTY}
        $LABEL_FAMILY: ${producto.family ?: MJS_FIELD_EMPTY}
        $LABEL_BRAND: ${producto.brand ?: MJS_FIELD_EMPTY}
        $LABEL_FAVORITE: ${if (producto.favorite) TEXT_YES else TEXT_NO}
        $LABEL_SITE: ${producto.permalink}
    """.trimIndent()


}