package com.tlb.testleboncoin.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tlb.core.domain.Title
import com.tlb.testleboncoin.databinding.ItemTitleBinding

class TitlesAdapter : RecyclerView.Adapter<TitlesViewHolder>() {
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
    ) = TitlesViewHolder(
        ItemTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TitlesViewHolder, position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount() = items.size

    class DiffCallback(
        private val oldItems: List<Title>,
        private val newItems: List<Title>
    ) : DiffUtil.Callback() {
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