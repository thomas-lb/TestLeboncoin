package com.tlb.testleboncoin.albums

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.AlbumList
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.base.BaseFragment
import com.tlb.testleboncoin.databinding.FragmentAlbumsBinding

class AlbumsFragment(
    private val viewModel: AlbumsViewModel
) : BaseFragment<FragmentAlbumsBinding>(
    FragmentAlbumsBinding::inflate
) {
    private val adapter = AlbumListAdapter(::onAlbumClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.albumsData.observe(this, ::onAlbums)
        viewModel.errorData.observe(this, ::onError)
        viewModel.loadingData.observe(this, ::onLoading)

        val spanCount =
            if (resources.getBoolean(R.bool.is_tablet)) {
                4
            } else 2
        binding.apply {
            albums.layoutManager = GridLayoutManager(
                requireContext(),
                spanCount
            ).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(
                        position: Int
                    ) = when (position) {
                        in 0..1 -> spanCount
                        else -> 1
                    }
                }
            }
            adapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            albums.adapter = adapter

            retry.setOnClickListener {
                viewModel.fetchData()
            }
        }
    }

    private fun onAlbums(albumList: AlbumList) {
        binding.apply {
            errorGroup.isVisible = false
            albums.isVisible = true
        }
        adapter.albumList = albumList
    }

    private fun onError(error: Result.Error<*>) {
        binding.apply {
            errorLabel.setText(
                when (error) {
                    is Result.Error.NotFound -> R.string.not_found_error
                    is Result.Error.Unknown -> R.string.not_found_error
                }
            )
            errorGroup.isVisible = true
            albums.isVisible = false
        }
    }

    private fun onLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    private fun onAlbumClicked(album: Album) {
        findNavController().navigate(
            AlbumsFragmentDirections.goToAlbum(album.id)
        )
    }
}