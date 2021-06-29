package com.example.restapi
import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {

    @GET("Movies")
    fun getData(): Call<List<KontakData>>

    @POST("Movies")
    fun postKontak(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Movies", hasBody = true)
    fun updateKontak(
        @Field("id") id: Int,
        @Field("judul") judul: String,
        @Field("director") director: String,
        @Field("genre") genre: String,
        @Field("durasi") durasi: Int,
       ): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Movies", hasBody = true)
    fun deleteKontak(@Field("id") id: Int): Call<KontakData>
}