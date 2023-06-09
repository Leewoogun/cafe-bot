package com.cafebot.cafemenubot.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cafebot.cafemenubot.databinding.ActivityMainBinding
import com.cafebot.cafemenubot.databinding.ItemBotChattingBinding
import com.cafebot.cafemenubot.databinding.ItemInitialBinding
import com.cafebot.cafemenubot.databinding.ItemMyChattingBinding
import com.cafebot.cafemenubot.model.*
import kotlinx.coroutines.NonDisposableHandle.parent

private const val VIEW_TYPE_INITIAL = 0
private const val VIEW_TYPE_BOT_CHATTING = 1
private const val VIEW_TYPE_MY_CHATTING = 2

class CafeMenuAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val chattingBot = mutableListOf<ChattingBot>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_INITIAL -> CafeInitialViewHolder(ItemInitialBinding.inflate(inflater, parent, false))
            VIEW_TYPE_BOT_CHATTING -> CafeChatBotViewHolder(ItemBotChattingBinding.inflate(inflater, parent, false))
            VIEW_TYPE_MY_CHATTING -> CafeMyChatViewHolder(ItemMyChattingBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("viewHolder 생성 실패")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CafeInitialViewHolder -> {
                val item = chattingBot[position] as Initial
                holder.bind(item)
            }

            is CafeChatBotViewHolder -> {
                val item = chattingBot[position] as ChatBotData
                holder.bind(item)
            }

            is CafeMyChatViewHolder -> {
                val item = chattingBot[position] as MyChatting
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(chattingBot[position]){
            is Initial -> VIEW_TYPE_INITIAL
            is ChatBotData -> VIEW_TYPE_BOT_CHATTING
            is MyChatting -> VIEW_TYPE_MY_CHATTING
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return chattingBot.size
    }

    fun addMessage(message : ChattingBot){
        chattingBot.add(message)
        notifyItemInserted(chattingBot.size - 1)
        recyclerView.scrollToPosition(chattingBot.size - 1)
    }

    fun resetChatBot(){
        chattingBot.clear()
    }


    class CafeInitialViewHolder(private val binding : ItemInitialBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(initial: Initial){
            binding.initial = initial
            binding.executePendingBindings()
        }
    }

    class CafeChatBotViewHolder(private val binding : ItemBotChattingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(chatBotData: ChatBotData){
            binding.chatBotData = chatBotData
            binding.executePendingBindings()
        }
    }

    class CafeMyChatViewHolder(private val binding : ItemMyChattingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(myChatting: MyChatting){
            binding.myChatting = myChatting
            binding.executePendingBindings()
        }
    }
}
