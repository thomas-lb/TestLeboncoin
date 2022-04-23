package com.tlb.testleboncoin.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.databinding.ItemAlbumSimpleBinding

class SimpleAlbumAdapter(
    private val albumClicked: (Album) -> Unit
) : RecyclerView.Adapter<SimpleAlbumViewHolder>() {
    var items = listOf<Album>()
        set(value) {
            DiffUtil.calculateDiff(
                SimpleAlbumDiffCallback(field, value)
            ).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleAlbumViewHolder(
        ItemAlbumSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SimpleAlbumViewHolder, position: Int) {
        holder.bind(items[position], albumClicked)
    }

    override fun getItemCount() = items.size

    inner class SimpleAlbumDiffCallback(
        private val oldItems: List<Album>,
        private val newItems: List<Album>
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