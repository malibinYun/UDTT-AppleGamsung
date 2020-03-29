package com.udtt.applegamsung.ui.applebox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ItemAppleBoxBinding

class AppleBoxAdapter : ListAdapter<AppleBoxItem, AppleBoxAdapter.ViewHolder>(DiffCallBack()) {

    private var itemClickListener: AppleBoxItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppleBoxBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appleBoxItem = getItem(position)
        holder.bind(appleBoxItem)
    }

    fun setItemClickListener(listener: AppleBoxItemClickListener?) {
        itemClickListener = listener
    }

    inner class ViewHolder(
        private val binding: ItemAppleBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(appleBoxItem: AppleBoxItem) {
            binding.appleBoxItem = appleBoxItem
            binding.itemClickListener = itemClickListener
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