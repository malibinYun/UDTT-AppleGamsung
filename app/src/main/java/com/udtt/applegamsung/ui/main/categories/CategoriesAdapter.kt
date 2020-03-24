package com.udtt.applegamsung.ui.main.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.databinding.ItemCategoryBinding
import com.udtt.applegamsung.ui.main.MainViewModel

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val categories = ArrayList<Category>()
    private var mainViewModel: MainViewModel? = null
    private var lifecycleOwner: LifecycleOwner? = null

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

    fun dataBindingWith(mainViewModel: MainViewModel, lifecycleOwner: LifecycleOwner) {
        this.mainViewModel = mainViewModel
        this.lifecycleOwner = lifecycleOwner
    }

    inner class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
            binding.mainViewModel = mainViewModel
        }
    }
}