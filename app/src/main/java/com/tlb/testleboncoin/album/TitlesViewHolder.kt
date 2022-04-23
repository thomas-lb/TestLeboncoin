package com.tlb.testleboncoin.album

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tlb.core.domain.Title
import com.tlb.testleboncoin.databinding.ItemTitleBinding

class TitlesViewHolder(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int, item: Title) = binding.apply {
        index.text = position.plus(1).toString()
        picture.load(item.thumbnailUrl)
        name.text = item.title
    }
}