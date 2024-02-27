package com.kuzmin.animals.feature.home.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.domain.model.Fact
import com.kuzmin.animals.feature.home.ui.AnimalFragment
import com.kuzmin.animals.feature.home.ui.PhotoAnimalFragment

class AnimalPagerAdapter(
    fragment: AnimalFragment,
    private val photos: List<AnimalPhoto>,
    private val facts: List<Fact>
) : FragmentStateAdapter(fragment) {

    var count: Int = -1

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun createFragment(position: Int): Fragment {
        count = position
        return PhotoAnimalFragment.newInstance(
            photos[position].medium,
            facts[position % facts.size].fact
        )
    }


}