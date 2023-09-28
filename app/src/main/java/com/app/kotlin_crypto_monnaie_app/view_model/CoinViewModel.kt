package com.app.kotlin_crypto_monnaie_app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.app.kotlin_crypto_monnaie_app.data.model.Coin
import com.app.kotlin_crypto_monnaie_app.data.model.CoinDetails
import com.app.kotlin_crypto_monnaie_app.data.repository.CoinRepository
import com.app.kotlin_crypto_monnaie_app.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {
    private val coinRepository = CoinRepository()

    private val _coin = MutableLiveData<List<Coin>>()
    val coin: LiveData<List<Coin>> = _coin

    private val _detailCoin = MutableLiveData<CoinDetails>()
    val detailCoin : LiveData<CoinDetails> = _detailCoin

    init {
        getCoinData()
    }

    public fun getCoinData() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            val coinsData = coinRepository.getCoins()
            emit(Resource.Success(data = coinsData))
        }catch (e:Exception){
            emit(Resource.Error(data = null, message = "Une erreur est survenu"))
        }
        /*viewModelScope.launch(Dispatchers.IO) {
            _coin.postValue(coinRepository.getCoins())
        }*/
    }

    public fun getCoinById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailCoin.postValue(coinRepository.getCoin(id))
        }
    }

}