package com.ricardocenteno.laundry.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ricardocenteno.laundry.R
import com.ricardocenteno.laundry.databinding.LoginFragmentBinding
import com.ricardocenteno.laundry.viewmodel.LoginViewModel
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var navigationController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding = LoginFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigate.observe(this, Observer { if (it) navigateToMachines() })
    }

    private fun navigateToMachines() {
        viewModel.navigate.value = false
        hideKeyboardFrom(context, view)
        navigationController?.navigate(
            R.id.action_loginFragment_to_machinesFragment
        )
    }

    private fun hideKeyboardFrom(context: Context?, view: View?) {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
