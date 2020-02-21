package com.ricardocenteno.laundry.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardocenteno.laundry.models.Washer
import com.ricardocenteno.laundry.repository.WasherMachinesRepository
import com.ricardocenteno.laundry.view.WasherListAdapter
import kotlin.random.Random

class MachinesViewModel : ViewModel() {
    private lateinit var adapter: WasherListAdapter
    val machines =  MutableLiveData<MutableList<Washer>>()
    val balance = ObservableField(30)
    var repository: WasherMachinesRepository? = null

    fun init() {
        machines.value = repository?.getMachines()
        adapter = WasherListAdapter(this)
        adapter.notifyDataSetChanged()
    }

    fun getWasherAt(position: Int): Washer? {
        return machines.value?.get(position)
    }

    fun onDeleteWasher(position: Int) {
        machines.value?.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    fun onAddBalance() {
        balance.set(balance.get()?.plus(5))
    }

    fun onAddWasher() {
        val id = Random.nextInt(1, 100)
        machines.value?.add(
            Washer(
                id,
                "Washer $id",
                Washer.Status("Available")))
        adapter.notifyDataSetChanged()
    }

    fun getAdapter(): WasherListAdapter {
        return adapter
    }
}
