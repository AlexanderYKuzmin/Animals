package com.kuzmin.animals.feature.home.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.databinding.FragmentHomeBinding
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.ui.adapters.ParentAdapter
import com.kuzmin.animals.feature.home.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val drawable: AnimationDrawable = binding.rootView.background as AnimationDrawable
        with(drawable) {
            setEnterFadeDuration(2000)
            setExitFadeDuration(2000)
        }

        //homeViewModel.getDbTest()
        homeViewModel.getAllAnimals()
        homeViewModel.animalsByTypes.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    //TODO progress bar
                }

                is Result.Success -> {
                    Log.d("Db", "Result success!")
                    Log.d("Db", "Animals: ${it.animals}")
                    val parents = homeViewModel.prepareUiData(it.animals)
                    Log.d("Db", "parents: $parents")
                    binding.rvParent.adapter =
                        ParentAdapter(
                            parents, {
                                drawable.start()
                            }, { animal ->
                                findNavController().navigate(
                                    com.kuzmin.animals.common.R.id.action_home_nav_graph_to_animal_fragment,
                                    //bundleOf(getString(com.kuzmin.animals.common.R.string.animal_name_en) to name, getString(com.kuzmin.animals.common.R.string.animal_id) to id)
                                    bundleOf(getString(com.kuzmin.animals.common.R.string.animal) to animal)
                                    )
                            }
                        )
                }

                is Result.Error -> {
                    Log.e("Db", "ERROR: ${it.throwable}")
                }

                else -> throw RuntimeException("wrong response data from Firedatabase")
            }
        }
    }
}