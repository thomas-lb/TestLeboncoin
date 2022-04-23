package com.tlb.testleboncoin.album

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumHeaderBinding

class HeaderViewHolder(
    private val binding: ItemAlbumHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album) = with(binding) {
        picture.load(album.url)
        name.text = root.context.getString(
            R.string.album_title,
            album.id.toString()
        )
    }
}