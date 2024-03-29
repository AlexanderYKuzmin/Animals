package com.kuzmin.animals.feature.favorite.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.MainThread
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.kuzmin.animals.feature.api.api.AnimalPagerListener
import com.kuzmin.animals.feature.favorite.R
import com.kuzmin.animals.feature.favorite.ui.adapters.FavoritePagerAdapter
import com.kuzmin.animals.feature.favorite.ui.viewmodels.FavoritePagerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritePagerFragment : Fragment() {
    private var mediumUrls: Array<String>? = null
    private var title: String? = null
    private var index: Int = -1

    private var animalPagerListener: AnimalPagerListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mediumUrls = it.getStringArray(URL_KEY)
            title = it.getString(TITLE_KEY)
            index = it.getInt(INDEX_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animalPagerListener?.onAnimalPagerActivated(true, title)

        val  adapter = FavoritePagerAdapter(this, mediumUrls!!)
        val pagerView = view.findViewById<ViewPager2>(R.id.vp_favorites)

        lifecycleScope.launch {
            pagerView.adapter = adapter
            delay(100)
            pagerView.setCurrentItem(index, true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AnimalPagerListener) {
            animalPagerListener = context
        } else {
            throw RuntimeException("FavoritePagerListener is not implemented!")
        }
    }

    override fun onPause() {
        super.onPause()
        animalPagerListener?.onAnimalPagerActivated(false, null)
    }

    companion object {
        const val URL_KEY = "medium_url_key"
        const val TITLE_KEY = "title_key"
        const val INDEX_KEY = "index_key"
    }
}