package com.kuzmin.animals.feature.favorite.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.favorite.databinding.ItemFavoritePrevBinding
import com.squareup.picasso.Picasso
import kotlin.math.ceil

class FavoritePreviewAdapter(
    private val favorites: List<AnimalPhoto>,
    private val imageClickListener: (String) -> Unit
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

        /*val firstPos = position * 3
        val secondPos = position * 3  + 1
        val thirdPos = position * 3  + 2*/

        with(holder.binding) {
            Picasso.get().load(favorites[itemIndices[0]].small)
                .into(ivFavoritePrevOne)

            if (itemIndices[1] < favorites.size - 1) {
                Picasso.get().load(favorites[itemIndices[1]].small)
                    .into(ivFavoritePrevTwo)

                if (itemIndices[2] < favorites.size) {
                    Picasso.get().load(favorites[itemIndices[2]].small)
                        .into(ivFavoritePrevThree)
                }
            }

            ivFavoritePrevOne.setOnClickListener { imageClickListener.invoke(favorites[itemIndices[0]].medium!!) }
            ivFavoritePrevTwo.setOnClickListener { imageClickListener.invoke(favorites[itemIndices[1]].medium!!) }
            ivFavoritePrevThree.setOnClickListener { imageClickListener.invoke(favorites[itemIndices[2]].medium!!) }
        }
    }

    private fun setItemIndex(rvPosition: Int, itemQuantity: Int, itemPos: Int): Int {
        return rvPosition * itemQuantity + itemPos
    }

    inner class FavoriteViewHolder(val binding: ItemFavoritePrevBinding) : ViewHolder(binding.root) {

    }
}