package com.udtt.applegamsung.ui.main.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.databinding.ItemProductBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val products = mutableListOf<Product>()
    private var itemClickListener: ProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    fun initItemsWith(items: List<Product>) {
        products.clear()
        products.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ProductClickListener) {
        itemClickListener = listener
    }

    private fun createItemClickListener(product: Product) = View.OnClickListener { view ->
        toggleItemIsSelected(view)
        itemClickListener?.onProductClick(product, view.isSelected)
    }

    private fun toggleItemIsSelected(view: View) {
        view.isSelected = !view.isSelected
    }

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.btnProduct.isSelected = false
            binding.product = product
            binding.itemClickListener = createItemClickListener(product)
        }
    }
}