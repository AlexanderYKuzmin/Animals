package com.kuzmin.animals.feature.favorite.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzmin.animals.feature.favorite.ui.FavoriteItemFragment
import com.kuzmin.animals.feature.favorite.ui.FavoritePagerFragment

class FavoritePagerAdapter(
    fragment: FavoritePagerFragment,
    private val fPhotoUrls: Array<String>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fPhotoUrls.size
    }

    override fun createFragment(position: Int): Fragment {
        return FavoriteItemFragment.newInstance(
            fPhotoUrls[position]
        )
    }
}