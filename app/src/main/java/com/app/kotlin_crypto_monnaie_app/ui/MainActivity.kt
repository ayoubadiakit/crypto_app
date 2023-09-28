package com.app.kotlin_crypto_monnaie_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kotlin_crypto_monnaie_app.adapters.CoinAdapter
import com.app.kotlin_crypto_monnaie_app.adapters.onCoinListner
import com.app.kotlin_crypto_monnaie_app.data.model.Coin
import com.app.kotlin_crypto_monnaie_app.ui.DetailCoinActivity
import com.app.kotlin_crypto_monnaie_app.utils.Resource
import com.app.kotlin_crypto_monnaie_app.view_model.CoinViewModel

const val KEY_COIN = "key_coin"
class MainActivity : AppCompatActivity(), onCoinListner {
    private val coinViewModel: CoinViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_crypto)
        val coinAdapter = CoinAdapter(this)
        val layoutManager = LinearLayoutManager(this)

        val textViewError = findViewById<TextView>(R.id.textViewError)
        val progresseBar = findViewById<ProgressBar>(R.id.progressBar)

        coinViewModel.getCoinData().observe(this){state->
            when (state){
                //Si la resource est à l'etat Loading
                is Resource.Loading ->{
                    progresseBar.visibility = View.VISIBLE
                }
                //Si la resource est à l'etat Success (les donnés ont bien été chargé)
                is Resource.Success ->{
                    coinAdapter.submitList(state.data)
                    progresseBar.visibility = View.GONE
                }
                is Resource.Error ->{
                    textViewError.visibility = View.VISIBLE
                    textViewError.text = state.message
                    progresseBar.visibility = View.GONE
                }
            }


        }

        recyclerView.adapter = coinAdapter
        recyclerView.layoutManager = layoutManager
    }

    override fun onClickCoin(coin: Coin) {
        val intentInfoCoin = Intent(this, DetailCoinActivity::class.java)
        intentInfoCoin.putExtra(KEY_COIN, coin.id)
        startActivity(intentInfoCoin)
    }
}