package com.kuzmin.animals.feature.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.kuzmin.animals.feature.favorite.R
import com.squareup.picasso.Picasso

private const val URL_KEY = "medium_url_key"

class FavoriteItemFragment : Fragment() {

    private var mediumUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mediumUrl = it.getString(URL_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<AppCompatImageView>(R.id.iv_photo_favorite)

        Picasso.get().load(mediumUrl).into(imageView)
    }

    companion object {

        @JvmStatic
        fun newInstance(mediumUrl: String?) =
            FavoriteItemFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_KEY, mediumUrl)
                }
            }
    }
}