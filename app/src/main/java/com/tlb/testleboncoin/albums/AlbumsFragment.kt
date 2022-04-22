package com.tlb.testleboncoin.albums

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.tlb.core.domain.Album
import com.tlb.testleboncoin.base.BaseFragment
import com.tlb.testleboncoin.databinding.FragmentAlbumsBinding

class AlbumsFragment(
    private val viewModel: AlbumsViewModel
): BaseFragment<FragmentAlbumsBinding>(
    FragmentAlbumsBinding::inflate
) {
    private val adapter = AlbumsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.albumsData.observe(this, ::onAlbums)

        binding.titles.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.titles.adapter = adapter
    }

    private fun onAlbums(albums: List<Album>) {
        adapter.albums = albums
    }
}