package com.app.kotlin_crypto_monnaie_app.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.kotlin_crypto_monnaie_app.R
import com.app.kotlin_crypto_monnaie_app.data.model.Coin
import com.bumptech.glide.Glide

class CoinAdapter(val listner: onCoinListner) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinDiffUtil()) {

    inner class CoinViewHolder(itemView: View) : ViewHolder(itemView.rootView) {
        val coin_name = itemView.findViewById<TextView>(R.id.coin_name)
        val coin_status = itemView.findViewById<TextView>(R.id.coin_status)

        init {
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION){
                    val coin = getItem(adapterPosition)
                    listner.onClickCoin(coin)
                }
            }
        }

        fun bind(coin: Coin){
            coin_name.text = "${coin.rank}. ${coin.name} (${coin.symbol})"
            coin_status.text = if (coin.is_active) "active" else "inactive"
            coin_status.setTextColor(if (coin.is_active) Color.GREEN else Color.RED)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)
    }
}

class CoinDiffUtil:DiffUtil.ItemCallback<Coin>(){
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

}

interface onCoinListner{
    fun onClickCoin(coin: Coin)
}
