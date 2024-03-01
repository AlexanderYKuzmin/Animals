package com.kuzmin.animals.feature.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kuzmin.animals.feature.home.databinding.FragmentPhotoAnimalBinding
import com.squareup.picasso.Picasso

private const val PHOTO_URL = "param1"
private const val FACT = "param2"

class PhotoAnimalFragment : Fragment() {
    private var photoUrl: String? = null
    private var fact: String? = null

    private var _binding: FragmentPhotoAnimalBinding? = null
    private val binding: FragmentPhotoAnimalBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoUrl = it.getString(PHOTO_URL)
            fact = it.getString(FACT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFact.setOnTouchListener { view, _ ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            return@setOnTouchListener false
        }
        binding.tvFact.movementMethod = ScrollingMovementMethod()

        binding.tvFact.text = fact
        Picasso.get().load(photoUrl).into(binding.ivPhoto)
    }

    companion object {

        @JvmStatic
        fun newInstance(photoUrl: String?, fact: String) =
            PhotoAnimalFragment().apply {
                arguments = Bundle().apply {
                    putString(PHOTO_URL, photoUrl)
                    putString(FACT, fact)
                }
            }
    }
}