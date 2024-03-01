package com.kuzmin.animals.feature.home.ui

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.home.databinding.FragmentAnimalBinding
import com.kuzmin.animals.feature.home.domain.model.FlickrResult.*
import com.kuzmin.animals.feature.home.domain.model.MediaState
import com.kuzmin.animals.feature.home.ui.adapters.AnimalPagerAdapter
import com.kuzmin.animals.feature.home.ui.viewmodels.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class AnimalFragment : Fragment() {

    @Inject
    @ApplicationContext lateinit var appContext: Context
    /*private var animalNameEn: String? = null
    private var animalId: Int? = null*/
    private var animal: com.kuzmin.animals.feature.api.model.Animal? = null

    private var animalPagerListener: AnimalPagerListener? = null

    private val animalViewModel: AnimalViewModel by viewModels()

    private lateinit var adapter: AnimalPagerAdapter

    private lateinit var dialogFragment: InfoAnimalDialogFragment

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
                it.getParcelable(getString(com.kuzmin.animals.common.R.string.animal), com.kuzmin.animals.feature.api.model.Animal::class.java)
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

        animalViewModel.getAnimalResources(animal!!, getString(com.kuzmin.animals.common.R.string.path_pattern_sound))

        animalViewModel.photos.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {}
                is Success -> {
                    adapter = AnimalPagerAdapter(this, it.photos, it.facts)
                    binding.viewPagerContainer.adapter = adapter
                    setOnClickListenerInfoBtn(binding.fabInfo, it.info)
                    setOnClickListenerSoundBtn(binding.fabSound, it.mediaUrl)
                    setOnClickListenerFavoriteBtn(binding.fabFavorite, it.photos)
                    setOnClickListenerExcludeBtn(binding.fabExclude, it.photos)
                }
                is Error -> { Log.d("Flickr", "ERROR: ${it.throwable}")}
            }
        }

        animalViewModel.mediaPlayerState.observe(viewLifecycleOwner) {
            when(it) {
                is MediaState.Completed -> binding.fabSound.isSelected = false
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

    private fun setOnClickListenerSoundBtn(btn: View, mediaUrl: Uri) {
        btn.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected) animalViewModel.handleMedia(mediaUrl, MediaService.PLAY)
            else animalViewModel.handleMedia(command = MediaService.STOP)
        }
    }

    private fun setOnClickListenerInfoBtn(btn: View, info: String) {
        dialogFragment = InfoAnimalDialogFragment.newInstance(info)

        btn.setOnClickListener {
            dialogFragment.show(childFragmentManager, InfoAnimalDialogFragment.TAG)
        }
    }

    private fun setOnClickListenerFavoriteBtn(btn: View, photos: List<AnimalPhoto>) {
        btn.setOnClickListener {
            adapter.count
            animalViewModel.saveAnimalAsFavorite(photos[adapter.count])
            Snackbar.make(binding.root, "Фото сохранено в Избранное", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setOnClickListenerExcludeBtn(btn: View, photos: List<AnimalPhoto>) {
        btn.setOnClickListener {
            animalViewModel.saveAnimalAsExcluded(photos[adapter.count])
            Snackbar.make(binding.root, "Фото занесено в черный список", Snackbar.LENGTH_SHORT).show()
        }
    }

    interface AnimalPagerListener {
        fun onAnimalPagerActivated(isActive: Boolean, title: String?)
    }
}