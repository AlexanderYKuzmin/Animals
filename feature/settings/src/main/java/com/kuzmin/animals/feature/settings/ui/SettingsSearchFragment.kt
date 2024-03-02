package com.kuzmin.animals.feature.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.settings.R
import com.kuzmin.animals.feature.settings.databinding.FragmentSettingsSearchBinding
import com.kuzmin.animals.feature.settings.ui.viewmodels.SettingsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class SettingsSearchFragment : Fragment() {

    @ApplicationContext
    lateinit var appContext: Context

    private val settingsSearchViewModel: SettingsSearchViewModel by viewModels()

    private var _binding: FragmentSettingsSearchBinding? = null
    private val binding: FragmentSettingsSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinner()
    }

    private fun setSpinner() {
        val categories = AnimalType.entries.map { it.name }
        val adapter = context?.let {
            ArrayAdapter(it, R.layout.spinner_item, categories).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }

        binding.spType.adapter = adapter
    }

    companion object {
        fun newInstance(): SettingsSearchFragment {
            return SettingsSearchFragment()
        }
    }
}