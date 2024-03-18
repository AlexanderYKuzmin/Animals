package com.kuzmin.animals.feature.home.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuzmin.animals.common.R.*
import com.kuzmin.animals.common.R.id.action_home_nav_graph_to_animal_fragment
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.databinding.FragmentHomeBinding
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.model.Result.*
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
        println("onCreate view")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TEST", "onView created!")

        val drawable: AnimationDrawable = binding.rootView.background as AnimationDrawable
        setUpAnimation(drawable)

        homeViewModel.getAllAnimals()
        homeViewModel.animalsByTypes.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> { binding.pbLoading.visibility = View.VISIBLE }
                is Success -> {
                    val parents = homeViewModel.prepareUiData(it.animals)
                    binding.rvParent.adapter =
                        ParentAdapter(
                            parents, {
                                drawable.start()
                            }, { animal ->
                                findNavController().navigate(
                                        action_home_nav_graph_to_animal_fragment,
                                        bundleOf(getString(string.animal) to animal)
                                    )
                            }
                        )
                    binding.pbLoading.visibility = View.GONE
                }
                is Error -> {
                    Log.e("Db", "ERROR: ${it.throwable}")
                    binding.pbLoading.visibility = View.GONE
                }

                else -> throw RuntimeException("wrong response data from Firedatabase")
            }
        }
    }

    private fun setUpAnimation(drawable: AnimationDrawable) {
        with(drawable) {
            setEnterFadeDuration(2000)
            setExitFadeDuration(2000)
        }
    }
}