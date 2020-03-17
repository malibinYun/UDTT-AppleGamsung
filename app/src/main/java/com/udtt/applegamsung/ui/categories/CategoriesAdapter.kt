package com.udtt.applegamsung.ui.categories

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.databinding.ItemCategoryBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val categories = ArrayList<Category>()
    private var categoryClickListener: ((categoryId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    fun initCategories(categories: List<Category>) {
        if (this.categories.isEmpty()) {
            this.categories.addAll(categories)
            notifyDataSetChanged()
        }
    }

    fun setCategoryClickListener(listener: (categoryId: String) -> Unit) {
        categoryClickListener = listener
    }

    private fun createCategoryClickListener(categoryId: String) = View.OnClickListener {
        categoryClickListener?.invoke(categoryId)
    }

    inner class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
            binding.itemClickListener = createCategoryClickListener(category.id)
        }
    }
}