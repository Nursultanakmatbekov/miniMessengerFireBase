package com.example.minimessengerfirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minimessengerfirebase.databinding.ItemMessengeBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private var list: List<String> = ArrayList()

    fun setData(listData: MutableList<String>) {
            this.list = listData
            notifyDataSetChanged()
        }

    class ViewHolder(private val binding: ItemMessengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(s: String) {
            binding.itemMessengeTitle.text = s
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMessengeBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}