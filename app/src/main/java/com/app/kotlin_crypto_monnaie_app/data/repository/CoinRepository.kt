package com.app.kotlin_crypto_monnaie_app.data.repository

import com.app.kotlin_crypto_monnaie_app.data.data_source.remote.ApiService
import com.app.kotlin_crypto_monnaie_app.data.model.Coin
import com.app.kotlin_crypto_monnaie_app.data.model.CoinDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CoinRepository {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinpaprika.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceApi = retrofit.create(ApiService::class.java)

    suspend fun getCoins(): List<Coin> {
        return serviceApi.getAllCoins()
    }

    suspend fun getCoin(id: String): CoinDetails {
        return serviceApi.getCoinById(id)
    }

}