package com.kuzmin.animals.feature.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzmin.animals.feature.settings.R
import com.kuzmin.animals.feature.settings.databinding.FragmentSettingsBinding
import com.kuzmin.animals.feature.settings.ui.adapters.SettingsPagerTabAdapter
import com.kuzmin.animals.feature.settings.ui.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpSettings.adapter = SettingsPagerTabAdapter(this)

        TabLayoutMediator(binding.tabSettings, binding.vpSettings) { tab, position ->
            val tabNames = resources.getStringArray(R.array.tab_names)
            tab.text = tabNames[position]
        }.attach()
    }
}