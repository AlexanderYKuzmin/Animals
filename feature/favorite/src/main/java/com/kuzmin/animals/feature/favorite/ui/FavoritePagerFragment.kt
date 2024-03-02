package com.kuzmin.animals.feature.favorite.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.kuzmin.animals.feature.favorite.R
import com.kuzmin.animals.feature.favorite.ui.adapters.FavoritePagerAdapter
import com.kuzmin.animals.feature.favorite.ui.viewmodels.FavoritePagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePagerFragment : Fragment() {
    private var mediumUrls: Array<String>? = null

    private val favoritePagerViewModel: FavoritePagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mediumUrls = it.getStringArray(URL_KEY)
            Log.d("Favorites", "medium urls $mediumUrls : ${mediumUrls?.size}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  adapter = FavoritePagerAdapter(this, mediumUrls!!)

        view.findViewById<ViewPager2>(R.id.vp_favorites).adapter = adapter
    }

    companion object {
        const val URL_KEY = "medium_url_key"
    }
}