package com.kuzmin.animals.feature.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.kuzmin.animals.feature.favorite.R
import com.squareup.picasso.Picasso

class FavoriteSinglePhotoFragment : Fragment() {
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_single_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivAnimal = view.findViewById<ImageView>(R.id.iv_animal)

        Picasso.get().load(url).into(ivAnimal)
    }

    companion object {
        const val URL_KEY = "url"
    }
}