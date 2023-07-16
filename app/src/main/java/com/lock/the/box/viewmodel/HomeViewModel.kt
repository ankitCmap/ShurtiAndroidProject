package com.lock.the.box.viewmodel

import android.app.ProgressDialog
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lock.the.box.model.BannerModel
import com.lock.the.box.model.BaseResponseData
import com.lock.the.box.model.HomeModel
import com.lock.the.box.model.ProductModel
import com.lock.the.box.model.StoreWiseProductModel
import com.lock.the.box.network.Resource
import com.lock.the.box.repository.HomeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(val homeRepository: HomeRepository):ViewModel() {

    val error: MutableLiveData<String>? = null
   val homeDataList : MutableLiveData<HomeModel> by lazy {
       MutableLiveData<HomeModel>()
   }
     val storeWiseProduct : MutableLiveData<List<StoreWiseProductModel>> by lazy {
        MutableLiveData<List<StoreWiseProductModel>>()
    }

    val productList : MutableLiveData<List<ProductModel>> by lazy {
        MutableLiveData<List<ProductModel>>()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        handleError()
    }
    fun homeResponse(
        hashMap: HashMap<String, Any> = HashMap<String, Any>(),
        activity: FragmentActivity?
    ) {
        val pd = ProgressDialog(activity)
        pd.setMessage("please wait..")
        pd.show()
        viewModelScope.launch(exceptionHandler) {
            homeRepository.homeDataRequest(hashMap).let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data != null) {
                            homeResponseData(it.data)
                            pd.dismiss()
                            Log.d("prabal", "pratap")
                            Log.d("prabal_data", it.data.toString())
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun homeResponseData(responseData: HomeModel) {
        synchronized(homeDataList) {
            homeDataList.postValue(responseData)
            storeWiseProduct.postValue(responseData.store_wise_product)
            productList.postValue(responseData.store_wise_product[0].product_list[0])
            //  val intent: Intent = Intent(this,Das)
        }
    }

    private fun handleError() {
        error?.let {
            synchronized(it) {
                error.postValue("")
            }
        }
    }

    class Factory(private val homeRepository: HomeRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(homeRepository) as T
        }
    }


}