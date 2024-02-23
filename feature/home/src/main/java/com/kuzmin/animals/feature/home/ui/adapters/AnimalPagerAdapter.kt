package com.kuzmin.animals.feature.home.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzmin.animals.feature.home.domain.model.Fact
import com.kuzmin.animals.feature.home.ui.AnimalFragment
import com.kuzmin.animals.feature.home.ui.PhotoAnimalFragment

class AnimalPagerAdapter(
    fragment: AnimalFragment,
    private val photoUrls: List<String>,
    private val facts: List<Fact>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return photoUrls.size
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoAnimalFragment.newInstance(
            photoUrls[position],
            facts[position % facts.size].fact
        )
    }
}