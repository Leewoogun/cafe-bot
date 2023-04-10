package com.cafebot.cafemenubot.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cafebot.cafemenubot.databinding.ItemInitialBinding

private const val VIEW_TYPE_INITIAL = 0
private const val VIEW_TYPE_BOT_CHATTING = 1
private const val VIEW_TYPE_MY_CHATTING = 2

class CafeMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class CafeMenuViewHolder(private val binding : ItemInitialBinding){

    }
}