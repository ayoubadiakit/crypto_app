package com.app.kotlin_crypto_monnaie_app.data.data_source.remote

import com.app.kotlin_crypto_monnaie_app.data.model.Coin
import com.app.kotlin_crypto_monnaie_app.data.model.CoinDetails
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiService {

    @GET("coins/")
    suspend fun getAllCoins():List<Coin>

    @GET("coins/{coin_id}")
    suspend fun getCoinById(@Path("coin_id") id:String):CoinDetails

}