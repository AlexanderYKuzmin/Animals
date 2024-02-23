package com.kuzmin.animals.feature.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.databinding.ItemParentBinding
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.getNameRu
import com.kuzmin.animals.feature.home.ui.model.ParentItem

class ParentAdapter(
    val parents: List<ParentItem>,
    val startAnimationListener: () -> Unit,
    val onAnimalClickListener: (String, Int) -> Unit
    ) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
    }
    override fun getItemCount() = parents.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(parents[position])
        startAnimationListener.invoke()
    }

    inner class ParentViewHolder( val binding: ItemParentBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rvChild.setHasFixedSize(true)
            binding.rvChild.layoutManager = LinearLayoutManager(binding.root.context)

            binding.cardParent.setOnClickListener {
                val upAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.up)
                val downAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.down)

                val parent = parents[adapterPosition]

                parent.isOpen = !parent.isOpen

                if (parent.isOpen) {
                    binding.cardParent.startAnimation(downAnim)
                    binding.rvChild.startAnimation(downAnim)
                } else {
                    binding.cardParent.startAnimation(upAnim)
                }
                notifyItemChanged(adapterPosition, Unit)
            }
        }

        fun bind(parentItem: ParentItem) {
            binding.tvType.text = parentItem.title.getNameRu()
            binding.ivAnimal.setImageResource(parentItem.image)

            when (parentItem.isOpen) {
                true -> setChildRecyclerView(parentItem.children)
                false -> binding.rvChild.visibility = View.GONE
            }
        }

        private fun setChildRecyclerView(children: List<Animal>) {
            binding.rvChild.visibility = View.VISIBLE
            binding.rvChild.adapter = ChildAdapter(
                children,
                onAnimalClickListener
                )
        }
       /* private fun isAnyItemOpened(position: Int) {

            if (position != RecyclerView.NO_POSITION){

                //var itemPosOpened : Int? = null
                val temp = parents.indexOfFirst { it.isOpen }

                if (temp != -1 && temp != position) {

                    if (parents[temp].isOpen){
                        parents[temp].isOpen = false
                    }

                    notifyItemChanged(temp, itemPosOpened)
                }
            }


        }*/
    }
}