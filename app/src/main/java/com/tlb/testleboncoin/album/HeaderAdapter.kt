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
): RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemAlbumHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(album)
    }

    override fun getItemCount() = 1

    class ViewHolder(
        private val binding: ItemAlbumHeaderBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) = with(binding) {
            picture.load(album.url)
            name.text = root.context.getString(
                R.string.album_title,
                album.id.toString()
            )
        }
    }
}