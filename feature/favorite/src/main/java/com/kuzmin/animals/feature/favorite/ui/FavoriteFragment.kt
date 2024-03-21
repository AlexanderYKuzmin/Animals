package com.kuzmin.animals.feature.favorite.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuzmin.animals.common.R
import com.kuzmin.animals.common.extension.showShortMessage
import com.kuzmin.animals.feature.favorite.databinding.FragmentFavoriteBinding
import com.kuzmin.animals.feature.favorite.domain.model.FavoritesResult.*
import com.kuzmin.animals.feature.favorite.ui.adapters.FavoritePreviewAdapter
import com.kuzmin.animals.feature.favorite.ui.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel.getFavorites()

        favoriteViewModel.favorites.observe(viewLifecycleOwner) { it ->
            when(it) {
                is Loading -> { binding.pbLoadingFavorite.visibility = View.VISIBLE }
                is Success -> {
                    val adapter = FavoritePreviewAdapter(it.favoriteList) { list, title, index ->
                        findNavController().navigate(
                            R.id.action_favorite_nav_graph_to_photo_single_fragment,
                            bundleOf(
                                FavoritePagerFragment.URL_KEY to list.toTypedArray(),
                                FavoritePagerFragment.TITLE_KEY to title,
                                FavoritePagerFragment.INDEX_KEY to index
                            )
                        )
                    }
                    binding.rvFavorites.adapter = adapter
                    binding.pbLoadingFavorite.visibility = View.GONE
                }
                is Error -> {
                    requireActivity().showShortMessage(getString(R.string.error_loading_image))
                    binding.pbLoadingFavorite.visibility = View.GONE
                    Log.d("Db", "ERROR: something went wrong with getting favorites - $it")
                }
            }
        }
    }
}