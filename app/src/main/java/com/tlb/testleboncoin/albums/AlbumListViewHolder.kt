package com.tlb.testleboncoin.albums

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumGridBinding
import com.tlb.testleboncoin.databinding.ItemHorizontalScrollBinding

sealed class AlbumListViewHolder<VB : ViewBinding>(
    protected val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    class AlbumViewHolder(
        binding: ItemAlbumGridBinding
    ) : AlbumListViewHolder<ItemAlbumGridBinding>(binding) {

        fun bind(
            item: Album,
            albumClicked: (Album) -> Unit
        ) = with(binding) {
            card.setOnClickListener { albumClicked(item) }
            thumbnail.load(item.url)
            albumId.text = binding.root.context.getString(
                R.string.album_title,
                item.id.toString()
            )
        }
    }

    class HorizontalScrollViewHolder(
        binding: ItemHorizontalScrollBinding,
        albumClicked: (Album) -> Unit
    ) : AlbumListViewHolder<ItemHorizontalScrollBinding>(binding) {
        private val adapter = SimpleAlbumAdapter(albumClicked)

        init {
            with(binding) {
                items.layoutManager = LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                items.adapter = adapter
            }
        }

        fun bind(
            titleResId: Int,
            albums: List<Album>
        ) = with(binding) {
            title.setText(titleResId)
            adapter.items = albums
        }
    }
}