package com.kuzmin.animals.feature.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.databinding.FragmentAnimalBinding
import com.kuzmin.animals.feature.home.domain.model.FlickrResult
import com.kuzmin.animals.feature.home.domain.model.FlickrResult.*
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.ui.adapters.AnimalPagerAdapter
import com.kuzmin.animals.feature.home.ui.viewmodels.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment : Fragment() {

    private var animalNameEn: String? = null
    private var animalId: Int? = null

    private val animalViewModel: AnimalViewModel by viewModels()

    private var _binding: FragmentAnimalBinding? = null
    private val binding: FragmentAnimalBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animalNameEn = it.getString(getString(com.kuzmin.animals.common.R.string.animal_name_en))
            animalId = it.getInt(getString(com.kuzmin.animals.common.R.string.animal_id))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (animalNameEn.isNullOrEmpty()) throw RuntimeException("No animal name to search")
        animalViewModel.getPhotoUrls(animalNameEn!!, animalId!!)

        animalViewModel.photos.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {}
                is Success -> {
                    Log.d("Flickr", "DATA URLS: ${it.urls}")
                    Log.d("Flickr", "DATA FACTS: ${it.facts.joinToString(", ")}")
                    val adapter = AnimalPagerAdapter(this, it.urls, it.facts)
                    binding.viewPagerContainer.adapter = adapter
                }
                is Error -> { Log.d("Flickr", "ERROR: ${it.throwable}")}
            }
        }
    }
}