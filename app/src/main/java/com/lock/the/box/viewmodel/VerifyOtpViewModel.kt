package com.lock.the.box.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lock.the.box.model.OtpVerifyModel
import com.lock.the.box.model.SignUpData
import com.lock.the.box.network.Resource
import com.lock.the.box.repository.SignUpRepository
import com.lock.the.box.repository.VerifyOtpRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class VerifyOtpViewModel (val verifyOtpRepository: VerifyOtpRepository) : ViewModel() {
    val error: MutableLiveData<String>? = null
    private lateinit var context: Context
    val signUpResponse: MutableLiveData<OtpVerifyModel> by lazy {
        MutableLiveData<OtpVerifyModel>()
    }
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        handleError()
    }

    fun signUpResponse(hashMap: HashMap<String, Any> = HashMap<String, Any>()) {
        this.context = context
        viewModelScope.launch(exceptionHandler) {
            verifyOtpRepository.otpRequest(hashMap).let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data != null) {
                            verifyOtpResponseData(it.data)
                            Log.d("prabal_data", it.data.toString())
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun verifyOtpResponseData(otpVerifyModel: OtpVerifyModel) {
        synchronized(signUpResponse) {
            signUpResponse.postValue(otpVerifyModel)
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

    class Factory(private val verifyOtpRepository: VerifyOtpRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VerifyOtpViewModel(verifyOtpRepository) as T
        }
    }

}