package com.lock.the.box.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lock.the.box.helper.ShurtiApplication.Companion.instance
import com.lock.the.box.helper.Utils.checkForInternet
import com.lock.the.box.helper.Utils.showToast
import com.lock.the.box.model.MainModel
import com.lock.the.box.model.Meals
import com.lock.the.box.network.Resource
import com.lock.the.box.repository.MainRepository
import com.lock.the.box.roomdatabase.RoomModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainVM(private val mainRepo: MainRepository) :
    ViewModel() {
    lateinit var item: Meals
    val showLoader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    var search:Boolean=true
    var liveData: List<RoomModel> = ArrayList()
    var liveMeals: ArrayList<Meals>? = null
    val dataList: MutableLiveData<List<Meals>> by lazy {
        MutableLiveData<List<Meals>>()
    }

    fun getOnlineData(context: Context) {
        if (checkForInternet(context)) {
            synchronized(showLoader) { showLoader.postValue(true) }
            CoroutineScope(Dispatchers.IO).launch {
                mainRepo.getAllData(context).let {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            synchronized(showLoader) { showLoader.postValue(false) }
                            if (it.data != null)
                                handleLogInData(it.data, true)
                            else
                                handleError()
                        }
                        else -> {
                            handleError()
                        }
                    }
                }
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                liveData = mainRepo.allData(context)!!
                if (liveData != null)
                    liveMeals = ArrayList()
                for (item in liveData) {
//                    getlocalData(item)
                }
                dataList.postValue(liveMeals)
            }

        }
    }
     fun searchData(test:String,context: Context){
         if (checkForInternet(context)) {
             if (validation(test, context)) {
                 CoroutineScope(Dispatchers.IO).launch {
                     mainRepo.searchData(test).let {
                         when (it.status) {
                             Resource.Status.SUCCESS -> {
                                 synchronized(showLoader) { showLoader.postValue(false) }
                                 if (it.data != null)
                                     handleLogInData(it.data, false)
                                 else
                                     handleError()
                             }
                             else -> {
                                 handleError()
                             }
                         }
                     }
                 }
             }
         }else{
             showToast(context,"Please check internet connection")
         }
     }
    fun validation(test: String, context: Context):Boolean{
        if (test=="") {
            showToast(context,"Please Enter product name")
            return false
        }
        return true
    }
    fun insertData(context: Context, item: List<Meals>) {
        mainRepo.insertData(context, item)
    }

//    fun getlocalData(item: RoomModel) {
//
//        item.let {
//            val meals = Meals(
//                it.idMeal,
//                it.strMeal,
//                it.strDrinkAlternate,
//                it.strCategory,
//                it.strArea,
//                it.strInstructions,
//                it.strMealThumb,
//                it.strTags,
//                it.strYoutube,
//                it.strIngredient1,
//                it.strIngredient2,
//                it.strIngredient3,
//                it.strIngredient4,
//                it.strIngredient4,
//                it.strIngredient6,
//                it.strIngredient7,
//                it.strIngredient8,
//                it.strIngredient9,
//                it.strIngredient10,
//                it.strIngredient11,
//                it.strIngredient12,
//                it.strIngredient13,
//                it.strIngredient14,
//                it.strIngredient15,
//                it.strIngredient16,
//                it.strIngredient17,
//                it.strIngredient18,
//                it.strIngredient19,
//                it.strIngredient20,
//                it.strMeasure1,
//                it.strMeasure2,
//                it.strMeasure3,
//                it.strMeasure4,
//                it.strMeasure5,
//                it.strMeasure6,
//                it.strMeasure7,
//                it.strMeasure7,
//                it.strMeasure9,
//                it.sstrMeasure10,
//                it.strMeasure11,
//                it.strMeasure12,
//                it.strMeasure13,
//                it.strMeasure14,
//                it.strMeasure15,
//                it.strMeasure15,
//                it.strMeasuret16,
//                it.strMeasure17,
//                it.strMeasure19,
//                it.strMeasure20,
//                it.strSource,
//                it.strImageSource,
//                it.strCreativeCommonsConfirmed,
//                it.dateModified
//            )
//            liveMeals?.add(meals)
//        }
//
//    }

    private fun handleLogInData(data1: MainModel?, check: Boolean) {
        if (data1 != null) {
            synchronized(dataList) {
                dataList.postValue(data1.meals)
            }
            if (check) {
                instance?.let { insertData(it, data1.meals) }
            }
        }
    }

    private fun handleError() {

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: MainRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainVM(repository) as T
        }
    }
}