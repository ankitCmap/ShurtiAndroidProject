package com.lock.the.box.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lock.the.box.model.SignUpData
import com.lock.the.box.network.Resource
import com.lock.the.box.repository.SignUpRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpViewModel(val signUpRepository: SignUpRepository) : ViewModel() {
    val error: MutableLiveData<String>? = null
    val signUpResponse: MutableLiveData<SignUpData> by lazy {
        MutableLiveData<SignUpData>()
    }
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        handleError()
    }

    fun signUpResponse(hashMap: HashMap<String, Any> = HashMap<String, Any>()) {
        viewModelScope.launch(exceptionHandler) {
            signUpRepository.signUpRequest(hashMap).let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data != null) {
                            signUpResponseData(it.data)

                            Log.d("prabal", "pratap")
                            Log.d("prabal_data", it.data.toString())
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun signUpResponseData(signUpData: SignUpData) {
        synchronized(signUpResponse) {
            signUpResponse.postValue(signUpData)
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

    class Factory(private val signUpRepository: SignUpRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignUpViewModel(signUpRepository) as T
        }
    }

}