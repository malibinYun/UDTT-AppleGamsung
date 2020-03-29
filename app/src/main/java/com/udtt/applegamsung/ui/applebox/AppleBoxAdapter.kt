package com.udtt.applegamsung.ui.applebox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ItemAppleBoxBinding

class AppleBoxAdapter : ListAdapter<AppleBoxItem, AppleBoxAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppleBoxBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appleBoxItem = getItem(position)
        holder.bind(position + 1, appleBoxItem)
    }

    inner class ViewHolder(
        private val binding: ItemAppleBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, appleBoxItem: AppleBoxItem) {
            binding.position = position
            binding.appleBoxItem = appleBoxItem
        }
    }

    private class DiffCallBack : DiffUtil.ItemCallback<AppleBoxItem>() {
        override fun areItemsTheSame(oldItem: AppleBoxItem, newItem: AppleBoxItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AppleBoxItem, newItem: AppleBoxItem): Boolean {
            return oldItem == newItem
        }
    }
}