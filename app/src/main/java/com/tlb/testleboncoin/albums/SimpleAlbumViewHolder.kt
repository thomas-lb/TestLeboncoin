package com.tlb.testleboncoin.albums

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumSimpleBinding

class SimpleAlbumViewHolder(
    private val binding: ItemAlbumSimpleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        album: Album,
        albumClicked: (Album) -> Unit
    ) = with(binding) {
        card.setOnClickListener { albumClicked(album) }
        thumbnail.load(album.url)
        albumId.text = binding.root.context.getString(
            R.string.album_title,
            album.id.toString()
        )
    }
}