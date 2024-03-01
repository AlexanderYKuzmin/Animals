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
import com.kuzmin.animals.feature.favorite.databinding.FragmentFavoriteBinding
import com.kuzmin.animals.feature.favorite.domain.usecases.FavoritesResult.*
import com.kuzmin.animals.feature.favorite.ui.adapters.FavoritePreviewAdapter
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

        favoriteViewModel.favorites.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {
                    //TODO progressbar
                }
                is Success -> {
                    val adapter = FavoritePreviewAdapter(it.favoriteList) {
                        findNavController().navigate(
                            com.kuzmin.animals.common.R.id.action_favorite_nav_graph_to_photo_single_fragment,
                            bundleOf(FavoriteSinglePhotoFragment.URL_KEY to it)
                        )
                    }
                    binding.rvFavorites.adapter = adapter
                }
                is Error -> {
                    Log.d("Db", "ERROR: something went wrong with getting favorites - $it")
                }
            }
        }
    }
}