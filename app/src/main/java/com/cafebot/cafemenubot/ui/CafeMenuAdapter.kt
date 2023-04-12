package com.cafebot.cafemenubot.ui

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cafebot.cafemenubot.databinding.ItemBotChattingBinding
import com.cafebot.cafemenubot.databinding.ItemInitialBinding
import com.cafebot.cafemenubot.databinding.ItemMyChattingBinding
import com.cafebot.cafemenubot.model.ChatBot
import com.cafebot.cafemenubot.model.ChattingBot
import com.cafebot.cafemenubot.model.Initial
import com.cafebot.cafemenubot.model.MyChatting

private const val VIEW_TYPE_INITIAL = 0
private const val VIEW_TYPE_BOT_CHATTING = 1
private const val VIEW_TYPE_MY_CHATTING = 2

class CafeMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                val item = chattingBot[position] as ChatBot
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
            is ChatBot -> VIEW_TYPE_BOT_CHATTING
            is MyChatting -> VIEW_TYPE_MY_CHATTING
        }
    }

    override fun getItemCount(): Int {
        return chattingBot.size
    }

    class CafeInitialViewHolder(private val binding : ItemInitialBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(initial: Initial){
            binding.initial = initial
            binding.executePendingBindings()
        }
    }

    class CafeChatBotViewHolder(private val binding : ItemBotChattingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(chatBot: ChatBot){
            binding.chatBot = chatBot
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
