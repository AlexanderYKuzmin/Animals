package com.kuzmin.animals.feature.home.ui

import android.content.Context
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.home.databinding.FragmentAnimalBinding
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.FlickrResult.*
import com.kuzmin.animals.feature.home.domain.model.MediaState
import com.kuzmin.animals.feature.home.ui.adapters.AnimalPagerAdapter
import com.kuzmin.animals.feature.home.ui.viewmodels.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment : Fragment() {

    /*private var animalNameEn: String? = null
    private var animalId: Int? = null*/
    private var animal: Animal? = null

    private var animalPagerListener: AnimalPagerListener? = null

    private val animalViewModel: AnimalViewModel by viewModels()

    private var _binding: FragmentAnimalBinding? = null
    private val binding: FragmentAnimalBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            /*animalNameEn = it.getString(getString(com.kuzmin.animals.common.R.string.animal_name_en))
            animalId = it.getInt(getString(com.kuzmin.animals.common.R.string.animal_id))*/
            animal = if (VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(getString(com.kuzmin.animals.common.R.string.animal))
            } else {
                it.getParcelable(getString(com.kuzmin.animals.common.R.string.animal), Animal::class.java)
            }
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
        requireActivity().title = animal?.nameRu

        animalPagerListener?.onAnimalPagerActivated(true, animal?.nameRu?.uppercase())

        if (animal == null) throw RuntimeException("No animal name to search")
        //animalViewModel.getPhotoUrls(animalNameEn!!, animalId!!)
        animalViewModel.getAnimalResources(animal!!, getString(com.kuzmin.animals.common.R.string.path_pattern_sound))

        animalViewModel.photos.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {}
                is Success -> {
                    Log.d("Flickr", "DATA URLS: ${it.urls}")
                    Log.d("Flickr", "DATA FACTS: ${it.facts.joinToString(", ")}")
                    val adapter = AnimalPagerAdapter(this, it.urls, it.facts)
                    binding.viewPagerContainer.adapter = adapter
                    binding.ivSound.setOnClickListener { btn ->
                        btn.isSelected = !btn.isSelected

                        if (btn.isSelected) animalViewModel.handleMedia(it.mediaUrl, MediaService.PLAY)
                        else animalViewModel.handleMedia(command = MediaService.STOP)
                    }

                    //animalViewModel.handleMedia(it.mediaUrl, MediaService.PLAY)
                }
                is Error -> { Log.d("Flickr", "ERROR: ${it.throwable}")}
            }
        }

        animalViewModel.mediaPlayerState.observe(viewLifecycleOwner) {
            when(it) {
                is MediaState.Completed -> binding.ivSound.isSelected = false
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AnimalPagerListener) {
            animalPagerListener = context
        } else {
            throw RuntimeException("Activity must implement AnimalPagerListener")
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("Media", "STOP PLAYER")
        animalViewModel.handleMedia(command = MediaService.STOP)
        animalPagerListener?.onAnimalPagerActivated(false, null)
    }

    interface AnimalPagerListener {
        fun onAnimalPagerActivated(isActive: Boolean, title: String?)
    }
}