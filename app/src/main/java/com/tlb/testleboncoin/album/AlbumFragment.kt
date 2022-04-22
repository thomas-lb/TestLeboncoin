package com.tlb.testleboncoin.album

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.testleboncoin.R
import com.tlb.testleboncoin.base.BaseFragment
import com.tlb.testleboncoin.databinding.FragmentAlbumBinding

class AlbumFragment(
    private val viewModel: AlbumViewModel
) : BaseFragment<FragmentAlbumBinding>(
    FragmentAlbumBinding::inflate
) {
    private val adapter = TitleAdapter()
    private val args by navArgs<AlbumFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.albumData.observe(this, ::onAlbum)
        viewModel.errorData.observe(this, ::onError)
        viewModel.loadingData.observe(this, ::onLoading)

        binding.apply {
            titles.layoutManager = LinearLayoutManager(requireContext())
            adapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

            retry.setOnClickListener { viewModel.fetchData(args.albumId) }
        }

        viewModel.id = args.albumId
    }

    private fun onAlbum(album: Album) {
        binding.titles.adapter = ConcatAdapter(
            HeaderAdapter(album),
            adapter
        )
        adapter.items = album.titles
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
            titles.isVisible = false
        }
    }

    private fun onLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }
}