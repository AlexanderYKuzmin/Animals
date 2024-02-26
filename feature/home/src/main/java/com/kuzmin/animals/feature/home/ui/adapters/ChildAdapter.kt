package com.kuzmin.animals.feature.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.animals.feature.home.databinding.ItemChildBinding
import com.kuzmin.animals.feature.home.domain.model.Animal

class ChildAdapter(
    private val children: List<Animal>,
    private val onAnimalClickListener: (Animal) -> Unit
) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding =
            ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun getItemCount() = children.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = children[position]
        val item = holder.binding
        item.tvName.text = child.nameRu

        item.cardChild.setOnClickListener {
            onAnimalClickListener.invoke(child)
        }
    }

    inner class ChildViewHolder(
        val binding: ItemChildBinding
    ) : RecyclerView.ViewHolder(binding.root)
}