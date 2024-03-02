package com.kuzmin.animals.feature.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.kuzmin.animals.feature.settings.R
import com.kuzmin.animals.feature.settings.databinding.FragmentSettingsBinding
import com.kuzmin.animals.feature.settings.ui.adapters.SettingsPagerTabAdapter
import com.kuzmin.animals.feature.settings.ui.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setViewPager()

        binding.vpSettings.adapter = SettingsPagerTabAdapter(this)

        TabLayoutMediator(binding.tabSettings, binding.vpSettings) { tab, position ->
            val tabNames = resources.getStringArray(R.array.tab_names)
            tab.text = tabNames[position]
        }.attach()
        //setTabLayout()
    }

    /*private fun setTabLayout() {
        with(binding.tabSettings) {
            addTab(
                newTab().apply {
                    text = getString(R.string.tab_1_name)
                }
            )
            addTab(
                newTab().apply {
                    text = getString(R.string.tab_2_name)
                }
            )

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        binding.vpSettings.currentItem = tab.position
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })
        }
    }

    private fun setViewPager() {
        val adapter = SettingsPagerTabAdapter(this)

        with(binding.vpSettings) {
            this.adapter = adapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    with(binding.tabSettings) {
                        selectTab(
                            getTabAt(position)
                        )
                    }
                }
            })
        }
    }*/
}