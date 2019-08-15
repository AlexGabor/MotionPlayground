package com.alexgabor.motionplayground.scrollableheader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexgabor.motionplayground.databinding.MainMenuItemBinding

typealias AdapterListener = (ScrollableHeaderAdapter.Item) -> Unit
private typealias ViewHolderListener = (Int) -> Unit

class ScrollableHeaderAdapter : ListAdapter<ScrollableHeaderAdapter.Item, ScrollableHeaderAdapter.ViewHolder>(DiffUtilItemCallback) {

    private var listener: AdapterListener? = null
    private val viewHolderListener: ViewHolderListener = { listener?.invoke(getItem(it)) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(MainMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewHolderListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setClickListener(adapterListener: AdapterListener) {
        listener = adapterListener
    }

    data class Item(val title: String, val description: String)

    class ViewHolder(private val binding: MainMenuItemBinding, listener: ViewHolderListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }

        fun bind(item: Item) {
            binding.title.text = item.title
            binding.description.text = item.description
        }
    }

    object DiffUtilItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = true
    }
}