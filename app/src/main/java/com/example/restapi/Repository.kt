package com.example.restapi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASEURL = "http://192.168.43.2/rest_api_ci/index.php/movies/"
class Repository {
    companion object{
        private var retrofit: Retrofit? =null
        fun getDataAPI(): Retrofit{
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .build()
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit!!
        }
    }
}