package com.tlb.testleboncoin.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumBinding

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {
    var albums: List<Album> = listOf()
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
        ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(albums[position])
    }

    override fun getItemCount() = albums.size

    class ViewHolder(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) = with(binding) {
            thumbnail.load(item.url)
            albumId.text = binding.root.context.getString(
                R.string.album_title,
                item.id.toString()
            )
        }
    }

    class DiffCallback(
        private val oldItems: List<Album>,
        private val newItems: List<Album>
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