package com.ricardocenteno.laundry.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardocenteno.laundry.BR
import com.ricardocenteno.laundry.databinding.ItemWasherBinding
import com.ricardocenteno.laundry.viewmodel.MachinesViewModel

class WasherListAdapter(private val machinesViewModel: MachinesViewModel) : RecyclerView.Adapter<WasherListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWasherBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return machinesViewModel.machines.value?.let { return it.size } ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(machinesViewModel, position)
    }

    class ViewHolder(private val binding: ItemWasherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: MachinesViewModel, position: Int) {
            binding.setVariable(BR.viewmodel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}