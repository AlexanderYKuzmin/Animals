package com.kuzmin.animals.feature.settings.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzmin.animals.feature.settings.ui.SettingsAnimalsFragment
import com.kuzmin.animals.feature.settings.ui.SettingsFragment
import com.kuzmin.animals.feature.settings.ui.SettingsSearchFragment

class SettingsPagerTabAdapter(
    fragment: SettingsFragment

) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return SETTINGS_ITEMS_AMOUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SettingsSearchFragment.newInstance()
            1 -> SettingsAnimalsFragment.newInstance()
            else -> throw RuntimeException("Wrong tab position.")
        }
    }

    companion object {
        const val SETTINGS_ITEMS_AMOUNT = 2
    }
}