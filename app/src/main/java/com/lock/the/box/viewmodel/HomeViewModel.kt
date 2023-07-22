package com.lock.the.box.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lock.the.box.model.HomeModel
import com.lock.the.box.model.ProductModel
import com.lock.the.box.model.StoreWiseProductModel
import com.lock.the.box.network.Result
import com.lock.the.box.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String>? = null
    val homeDataList: MutableLiveData<HomeModel> =
        MutableLiveData<HomeModel>()

    val storeWiseProduct: MutableLiveData<List<StoreWiseProductModel>> = MutableLiveData<List<StoreWiseProductModel>>()


    val productList: MutableLiveData<List<ProductModel>> by lazy {
        MutableLiveData<List<ProductModel>>()
    }

    fun homeResponse(hashMap: HashMap<String, Any> = HashMap()) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.homeDataRequest(hashMap)) {
                is Result.Failure -> Log.e(TAG, "error: ${result.throwable}")
                is Result.Success -> {
                    homeDataList.postValue(result.data)
                    storeWiseProduct.postValue(result.data.store_wise_product)
                }
            }
            isLoading.postValue(false)
        }
    }

    private fun homeResponseData(responseData: HomeModel) {

    }

    private fun handleError() {
        error?.let {
            synchronized(it) {
                error.postValue("")
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}