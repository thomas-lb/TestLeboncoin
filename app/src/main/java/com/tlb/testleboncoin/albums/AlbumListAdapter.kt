package com.tlb.testleboncoin.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tlb.core.domain.Album
import com.tlb.core.interactors.AlbumList
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.databinding.ItemAlbumGridBinding
import com.tlb.testleboncoin.databinding.ItemHorizontalScrollBinding

class AlbumListAdapter(
    private val albumClicked: (Album) -> Unit
) : RecyclerView.Adapter<AlbumListViewHolder<out ViewBinding>>() {

    private var items: List<ItemType> = listOf()
        set(value) {
            DiffUtil.calculateDiff(
                DiffCallback(field, value)
            ).dispatchUpdatesTo(this)
            field = value
        }

    var albumList = AlbumList()
        set(value) {
            items = listOf<ItemType>()
                .run {
                    if (value.favorites.isNotEmpty()) {
                        plus(
                            ItemType.ItemHorizontalScroll(
                                R.string.favorites_section_title,
                                value.favorites
                            )
                        )
                    } else this
                }.run {
                    if (value.recommended.isNotEmpty()) {
                        plus(
                            ItemType.ItemHorizontalScroll(
                                R.string.recommended_section_title,
                                value.recommended
                            )
                        )
                    } else this
                }.plus(value.albums.map { ItemType.ItemAlbum(it) })

            field = value
        }

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_horizontal_scroll -> AlbumListViewHolder.HorizontalScrollViewHolder(
            ItemHorizontalScrollBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            albumClicked
        )
        else -> AlbumListViewHolder.AlbumViewHolder(
            ItemAlbumGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: AlbumListViewHolder<*>,
        position: Int
    ) {
        when (holder) {
            is AlbumListViewHolder.HorizontalScrollViewHolder -> {
                val item = items[position] as ItemType.ItemHorizontalScroll
                holder.bind(item.id, item.items)
            }
            is AlbumListViewHolder.AlbumViewHolder -> {
                val item = items[position] as ItemType.ItemAlbum
                holder.bind(item.item, albumClicked)
            }
        }
    }

    override fun getItemCount() = items.size

    class DiffCallback(
        private val oldItems: List<ItemType>,
        private val newItems: List<ItemType>
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

    sealed class ItemType(
        val id: Int,
        val viewType: Int
    ) {
        class ItemHorizontalScroll(
            titleResId: Int,
            val items: List<Album>
        ) : ItemType(
            titleResId,
            R.layout.item_horizontal_scroll
        )

        class ItemAlbum(val item: Album) : ItemType(
            item.id,
            R.layout.item_album_grid
        )
    }
}