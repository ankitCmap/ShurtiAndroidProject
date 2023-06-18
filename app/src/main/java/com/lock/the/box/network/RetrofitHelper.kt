package com.lock.the.box.network

import com.lock.the.box.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private val gson: Gson =
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().setLenient().create()

    @JvmStatic
    fun <S> createRetrofitService(serviceClass: Class<S>): S {
        return createService(serviceClass)
    }

    private fun <S> createService(serviceClass: Class<S>): S {
        return RetrofitObj.client.create(serviceClass)
    }

    private object RetrofitObj {

        val client: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                HttpManager.normalClient.newBuilder()
                    .callTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                   /* .addInterceptor(UnauthorizedInterceptor())*/
                    .build()
            )
            .build()
    }

  /*  internal class UnauthorizedInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            if (response.code == 500) {
                BasePreferencesManager.clearPref()
                val intent = Intent(Lts.applicationContext(), LogInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Lts.applicationContext().startActivity(intent)
            }
            return response
        }
    }*/
/*
        internal class BasicAuthInterceptor(user: String, password: String) : Interceptor {
        private val credentials: String = Credentials.basic(user, password)

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build()
            return chain.proceed(authenticatedRequest)
        }

    }
*/

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

}


