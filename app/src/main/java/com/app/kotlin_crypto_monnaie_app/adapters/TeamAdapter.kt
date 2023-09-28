package com.app.kotlin_crypto_monnaie_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.kotlin_crypto_monnaie_app.R
import com.app.kotlin_crypto_monnaie_app.data.model.Team

class TeamAdapter:ListAdapter<Team, TeamAdapter.TeamViewHolder>(TeamDiffUtils()) {
    class TeamViewHolder(itemView: View):ViewHolder(itemView.rootView) {
        val name_member = itemView.findViewById<TextView>(R.id.name_member)
        val fonction_member = itemView.findViewById<TextView>(R.id.fonction_member)

        fun bind(team: Team){
            name_member.text = team.name
            fonction_member.text = team.position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_membre, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team)
    }
}

class TeamDiffUtils:DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }

}
