package com.kuzmin.animals.feature.settings.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kuzmin.animals.common.extension.hideKeyboard
import com.kuzmin.animals.common.extension.showShortMessage
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.getEnumByRuName
import com.kuzmin.animals.feature.api.model.getNameRu
import com.kuzmin.animals.feature.settings.R
import com.kuzmin.animals.feature.settings.databinding.FragmentSettingsSearchBinding
import com.kuzmin.animals.feature.settings.domain.model.SettingsResult
import com.kuzmin.animals.feature.settings.domain.model.SettingsResult.*
import com.kuzmin.animals.feature.settings.ui.viewmodels.SettingsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class SettingsSearchFragment : Fragment() {

    @Inject
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

        setAnimalTypeSpinner()

        val type = binding.spType.selectedItem.toString().getEnumByRuName()

        val keywordsFields = arrayOf(binding.etKeyword1, binding.etKeyword2)

        settingsSearchViewModel.handleSelectedType(type)

        settingsSearchViewModel.searchAnimalSettings.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {
                //TODO
                }
                is Success -> {
                    binding.etPhotoQuantity.setText(it.quantity.toString())

                    if (it.tags.isNotEmpty()) {
                        for (i in 0 until it.tags.size) {
                            keywordsFields[i].setText(it.tags[i])
                        }
                    }
                    setSaveBtnOnClickListener()
                }
                is SuccessWriting -> {
                    appContext.showShortMessage(getString(R.string.tags_added_ok))
                }
                is Error -> {
                    Log.d("Settings", "ERROR: ${it.throwable}")
                }
            }
        }
    }

    private fun setAnimalTypeSpinner() {
        val categories = AnimalType.entries.filter { it != AnimalType.NO_TYPE }.map { it.getNameRu() }
        val adapter = context?.let {
            ArrayAdapter(it, R.layout.spinner_item, categories).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }

        binding.spType.adapter = adapter
    }

    private fun setSaveBtnOnClickListener() {
        with(binding) {
            saveBtn.setOnClickListener {
                val type = spType.selectedItem.toString().getEnumByRuName()
                val quantity = etPhotoQuantity.text.toString().toInt()
                val tags = listOf(
                    etKeyword1.text.toString(),
                    etKeyword2.text.toString()
                )
                hideKeyboard()
                settingsSearchViewModel.handleSaveSearchData(type, tags, quantity)
            }
        }

    }

    companion object {
        fun newInstance(): SettingsSearchFragment {
            return SettingsSearchFragment()
        }
    }
}