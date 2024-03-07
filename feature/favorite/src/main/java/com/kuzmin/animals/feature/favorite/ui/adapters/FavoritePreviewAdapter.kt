package com.kuzmin.animals.feature.favorite.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.favorite.databinding.ItemFavoritePrevBinding
import com.squareup.picasso.Picasso
import kotlin.math.ceil

class FavoritePreviewAdapter(
    private val favorites: List<AnimalPhoto>,
    private val imageClickListener: (List<String>, String, Int) -> Unit
) : RecyclerView.Adapter<FavoritePreviewAdapter.FavoriteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoritePrevBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ceil(favorites.size / 3.0).toInt()
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        if (favorites.isEmpty()) return
        val itemIndices = arrayOf(-1, -1, -1)
        for (i in itemIndices.indices) {
            itemIndices[i] =
                setItemIndex(position, itemIndices.size, i)
        }

        with(holder.binding) {
            Picasso.get().load(favorites[itemIndices[0]].small)
                .into(ivFavoritePrevOne)
            setOnClickListener(ivFavoritePrevOne, itemIndices[0])

            if (itemIndices[1] < favorites.size - 1) {
                Picasso.get().load(favorites[itemIndices[1]].small)
                    .into(ivFavoritePrevTwo)
                setOnClickListener(ivFavoritePrevTwo, itemIndices[1])

                if (itemIndices[2] < favorites.size) {
                    Picasso.get().load(favorites[itemIndices[2]].small)
                        .into(ivFavoritePrevThree)
                    setOnClickListener(ivFavoritePrevThree, itemIndices[2])
                }
            }
        }
    }

    private fun setItemIndex(rvPosition: Int, itemQuantity: Int, itemPos: Int): Int {
        return rvPosition * itemQuantity + itemPos
    }

    private fun setOnClickListener(view: ImageView, index: Int) {
        view.setOnClickListener {
            val favoritesTemp = favorites.filter { it.animalNameEn == favorites[index].animalNameEn }
            imageClickListener.invoke(
                favoritesTemp.map { it.medium ?: "" },
                favorites[index].animalNameRu,
                favoritesTemp.indexOf(favorites[index])
            )
        }
    }

    inner class FavoriteViewHolder(val binding: ItemFavoritePrevBinding) : ViewHolder(binding.root) {

    }
}