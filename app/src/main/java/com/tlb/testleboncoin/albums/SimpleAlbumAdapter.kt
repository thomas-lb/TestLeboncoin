package com.tlb.testleboncoin.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumSimpleBinding

class SimpleAlbumAdapter(
    private val albumClicked: (Album) -> Unit
): RecyclerView.Adapter<SimpleAlbumAdapter.SimpleAlbumViewHolder>() {
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
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SimpleAlbumViewHolder(
        private val binding: ItemAlbumSimpleBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) = with(binding) {
            card.setOnClickListener { albumClicked(album) }
            thumbnail.load(album.url)
            albumId.text = binding.root.context.getString(
                R.string.album_title,
                album.id.toString()
            )
        }
    }

    inner class SimpleAlbumDiffCallback(
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