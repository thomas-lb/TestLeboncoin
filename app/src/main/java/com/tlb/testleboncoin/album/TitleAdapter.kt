package com.tlb.testleboncoin.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Title
import com.tlb.testleboncoin.databinding.ItemTitleBinding

class TitleAdapter: RecyclerView.Adapter<TitleAdapter.ViewHolder>() {
    var items: List<Title> = listOf()
        set(value) {
            DiffUtil.calculateDiff(
                DiffCallback(field, value)
            ).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: ItemTitleBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Title) = binding.apply {
            index.text = adapterPosition.plus(1).toString()
            picture.load(item.thumbnailUrl)
            name.text = item.title
        }
    }

    class DiffCallback(
        private val oldItems: List<Title>,
        private val newItems: List<Title>
    ): DiffUtil.Callback() {
        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldItems[oldItemPosition].hashCode() == newItems[newItemPosition].hashCode()
    }
}