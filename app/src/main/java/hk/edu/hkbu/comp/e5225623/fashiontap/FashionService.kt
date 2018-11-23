package hk.edu.hkbu.comp.e5225623.fashiontap

import hk.edu.hkbu.comp.e5225623.fashiontap.json.PhotoResponse
import hk.edu.hkbu.comp.e5225623.fashiontap.json.PhotoStream
import hk.edu.hkbu.comp.e5225623.fashiontap.json.Photo
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FashionService {
    companion object {
        val instance: FashionService by lazy {
            Retrofit.Builder()
                .baseUrl("http://fashiontap.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FashionService::class.java)
        }
    }

    @GET("/photo/popular")
    fun getFashionPhoto(@Query("pageSize") pageSize: Int = 10,@Query("before") before: String = (System.currentTimeMillis()).toString()): Call<PhotoResponse>

}