package com.example.shurtiandroidproject.repository

import android.content.Context
import com.example.shurtiandroidproject.model.MainModel
import com.example.shurtiandroidproject.model.Meals
import com.example.shurtiandroidproject.network.Resource
import com.example.shurtiandroidproject.network.WebServices
import com.example.shurtiandroidproject.roomdatabase.AppDatabase
import com.example.shurtiandroidproject.roomdatabase.RoomModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainRepository(private val webServices: WebServices) {

    private fun getNetworkIODispatcher() = IO

    companion object {
        var mainpartDatabase: AppDatabase? = null

        var roomModel: List<RoomModel>? = null
          fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }
    }

    fun allData(context: Context) : List<RoomModel>? {
        mainpartDatabase = initializeDB(context)
        roomModel = mainpartDatabase!!.mainPartDeo().getAll()
        return roomModel
    }
     fun insertData(context: Context, itemData: List<Meals>) {
        mainpartDatabase = initializeDB(context)
         for (item in itemData) {
             CoroutineScope(IO).launch {
                 item.let {
                     val loginDetails = RoomModel(
                         it.idMeal,
                         it.strMeal,
                         it.strDrinkAlternate,
                         it.strCategory,
                         it.strArea,
                         it.strInstructions,
                         it.strMealThumb,
                         it.strTags,
                         it.strYoutube,
                         it.strIngredient1,
                         it.strIngredient2,
                         it.strIngredient3,
                         it.strIngredient4,
                         it.strIngredient4,
                         it.strIngredient6,
                         it.strIngredient7,
                         it.strIngredient8,
                         it.strIngredient9,
                         it.strIngredient10,
                         it.strIngredient11,
                         it.strIngredient12,
                         it.strIngredient13,
                         it.strIngredient14,
                         it.strIngredient15,
                         it.strIngredient16,
                         it.strIngredient17,
                         it.strIngredient18,
                         it.strIngredient19,
                         it.strIngredient20,
                         it.strMeasure1,
                         it.strMeasure2,
                         it.strMeasure3,
                         it.strMeasure4,
                         it.strMeasure5,
                         it.strMeasure6,
                         it.strMeasure7,
                         it.strMeasure7,
                         it.strMeasure9,
                         it.sstrMeasure10,
                         it.strMeasure11,
                         it.strMeasure12,
                         it.strMeasure13,
                         it.strMeasure14,
                         it.strMeasure15,
                         it.strMeasure15,
                         it.strMeasuret16,
                         it.strMeasure17,
                         it.strMeasure19,
                         it.strMeasure20,
                         it.strSource,
                         it.strImageSource,
                         it.strCreativeCommonsConfirmed,
                         it.dateModified
                     )
                     mainpartDatabase!!.mainPartDeo().insert(loginDetails)
                 }

             }
         }
    }

      suspend fun getAllData(context: Context): Resource<MainModel>  {
          mainpartDatabase = initializeDB(context)
          mainpartDatabase!!.mainPartDeo().deleteAllNotes()
        return withContext(getNetworkIODispatcher()) {
            try {
                val response = webServices.dalaList()
                if (response.isSuccessful && response.body() != null) {
                    Resource.success(response.body() as MainModel)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }

    suspend fun searchData( test: String): Resource<MainModel>  {
        return withContext(getNetworkIODispatcher()) {
            try {
                val response = webServices.searchByProducdName(test)
                if (response.isSuccessful && response.body() != null) {
                    Resource.success(response.body() as MainModel)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }
}