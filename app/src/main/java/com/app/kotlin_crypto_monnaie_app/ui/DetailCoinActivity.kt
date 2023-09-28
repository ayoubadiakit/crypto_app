package com.app.kotlin_crypto_monnaie_app.ui

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kotlin_crypto_monnaie_app.KEY_COIN
import com.app.kotlin_crypto_monnaie_app.R
import com.app.kotlin_crypto_monnaie_app.adapters.TeamAdapter
import com.app.kotlin_crypto_monnaie_app.view_model.CoinViewModel

class DetailCoinActivity : AppCompatActivity() {
    private val coinViewModel: CoinViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_coin)

        val name_coin = findViewById<TextView>(R.id.name_coin)
        val status_coin = findViewById<TextView>(R.id.status_coin)
        val description_coin = findViewById<TextView>(R.id.description_coin)

        val coin = intent.getStringExtra(KEY_COIN)

        if (coin != null) {
            coinViewModel.getCoinById(coin)
        }

        val recyclerViewFonction = findViewById<RecyclerView>(R.id.rv_fonction)
        val fonctionAdapter = TeamAdapter()
        val layoutManager = LinearLayoutManager(this)

        recyclerViewFonction.adapter = fonctionAdapter
        recyclerViewFonction.layoutManager = layoutManager

        val liste_empty = findViewById<TextView>(R.id.liste_empty)
        val description_empty = findViewById<TextView>(R.id.description_empty)

        coinViewModel.detailCoin.observe(this) {
            name_coin.text = "${it.rank}. ${it.name} (${it.symbol})"

            status_coin.text = if (it.is_active) "active" else "inactive"
            status_coin.setTextColor(if (it.is_active) Color.GREEN else Color.RED)

            if(it.description.isNotEmpty()){
                description_empty.visibility = View.GONE
                description_coin.text = it.description
            }else{
                description_empty.visibility = View.VISIBLE
                description_empty.text = "Pas de description pour cette monnaie"
                description_empty.setTextColor(Color.RED)
            }

            if(it.team.isEmpty()){
                liste_empty.visibility = View.VISIBLE
                liste_empty.text = "Pas d'echange pour le moment"
                liste_empty.setTextColor(Color.RED)

            }else{
                liste_empty.visibility = View.GONE
                fonctionAdapter.submitList(it.team)

            }

        }

    }
}