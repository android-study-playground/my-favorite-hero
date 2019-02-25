package com.br.myfavoritehero.data.network

import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Comic
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
            @Query("offset") offset: Int = 0,
            @Query("limit") limit: Int = 20
    ): Call<BaseResponse<Hero>>

    @GET("v1/public/comics")
    fun getComics(
            @Query("ts") ts: Long,
            @Query("apikey") apiKey: String,
            @Query("hash") hash: String,
            @Query("characters") characters: String = "",
            @Query("offset") offset: Int = 0,
            @Query("limit") limit: Int = 20
    ): Call<BaseResponse<Comic>>

}