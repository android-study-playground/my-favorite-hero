package com.br.myfavoritehero.data.network

import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Hero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    fun getHeroes(
        @Query("ts") ts: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 0
    ): Call<BaseResponse<Hero>>

}