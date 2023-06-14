package com.example.inhilenttest.compose.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inhilenttest.util.Constant
import com.example.inhilenttest.api.MyRepository
import com.example.inhilenttest.data.DataList
import com.example.inhilenttest.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    val productLiveData = MutableLiveData<DataList>()
    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.getProducts()
                productLiveData.postValue(products)
            } catch (e: Exception) {
                Log.e("TAG", "something wrong!")
            }
        }
    }

    // product click to view details
    var product by mutableStateOf<Product?>(null)
        private set

    fun addProduct(newproduct: Product) {
        product = newproduct
    }

    //    on title change
    val titleChange = MutableLiveData(Constant.PRODUCTS)
    fun setTitle(title: String) {
        titleChange.postValue(title)
    }

    //  add favorite product
    fun addRemoveFevProduct(product: Product) {
        productLiveData.value?.products?.filter {
            it.id == product.id
        }?.map {
            it.apply {
                isInWishlist = product.isInWishlist
            }
        }
        productLiveData.postValue(productLiveData.value)
    }
}