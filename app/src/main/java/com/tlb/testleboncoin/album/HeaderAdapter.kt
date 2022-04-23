package com.tlb.testleboncoin.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumHeaderBinding

class HeaderAdapter(
    val album: Album
) : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HeaderViewHolder(
        ItemAlbumHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(album)
    }

    override fun getItemCount() = 1
}