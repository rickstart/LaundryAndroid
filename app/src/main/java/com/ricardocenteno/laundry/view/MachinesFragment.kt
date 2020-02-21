package com.ricardocenteno.laundry.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ricardocenteno.laundry.R
import com.ricardocenteno.laundry.databinding.MachinesFragmentBinding
import com.ricardocenteno.laundry.repository.WasherMachinesRepository
import com.ricardocenteno.laundry.viewmodel.MachinesViewModel

class MachinesFragment : Fragment() {

    lateinit var navController: NavController
    private lateinit var viewModel: MachinesViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(MachinesViewModel::class.java)
        viewModel.repository = context?.let { WasherMachinesRepository(it) }
        val binding = MachinesFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        recyclerView = binding.rvMachines
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> viewModel.onAddWasher()
        }
        return super.onOptionsItemSelected(item)
    }
}
