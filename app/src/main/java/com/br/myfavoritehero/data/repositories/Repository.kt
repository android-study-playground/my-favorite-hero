package com.br.myfavoritehero.data.repositories

import android.util.Log
import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Data
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.network.ApiService
import com.br.myfavoritehero.util.Util
import retrofit2.Call
import retrofit2.Callback

class Repository(private val apiService: ApiService){

        fun getHeroes(
            success: (base: BaseResponse<Hero>) -> Unit,
            error: (t: Throwable) -> Unit
    ){
        val ts = (System.currentTimeMillis() / 1000)
        val hash = Util.md5(ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

        val call = apiService.getHeroes(ts, BuildConfig.PUBLIC_KEY, hash)
        Log.d("URL: ", call.request().url().toString())
        call.enqueue(
            object: Callback<BaseResponse<Hero>> {
                override fun onFailure(call: Call<BaseResponse<Hero>>, t: Throwable) {
                    error(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<Hero>>,
                    baseResponse: retrofit2.Response<BaseResponse<Hero>>
                ) {
                    if(baseResponse.code() == 200) {
                        success(baseResponse.body()!!)
                    }else{
                       error(Throwable())
                    }
                }
            }
        )
    }

}
