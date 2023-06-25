package com.lock.the.box.viewmodel

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
    val error : MutableLiveData<String>?= null
    val signUpResponse : MutableLiveData<SignUpData> by lazy {
        MutableLiveData<SignUpData>()
    }
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        handleError()
    }

    fun signUpResponse(jsonObject: JSONObject){
        viewModelScope.launch(exceptionHandler){
            signUpRepository.signUpRequest(jsonObject).let {
                when(it.status){
                    Resource.Status.SUCCESS->{
                        if (it.data !=null){
                           signUpResponseData(it.data)
                            Log.d("prabal","pratap")
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun signUpResponseData(signUpData: SignUpData){
        synchronized(signUpResponse){
            signUpResponse.postValue(signUpData)
        }
    }

    private fun handleError(){
        error?.let {
            synchronized(it){
                error.postValue("")
            }
        }
    }

    class Factory(private val signUpRepository: SignUpRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignUpViewModel(signUpRepository) as T
        }
    }

}