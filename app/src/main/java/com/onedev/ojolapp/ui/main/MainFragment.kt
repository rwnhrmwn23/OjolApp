package com.onedev.ojolapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.databinding.FragmentMainBinding
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.Constant.TOKEN
import com.onedev.ojolapp.utils.getStringPreference
import com.onedev.ojolapp.utils.showToast
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : ScopeFragment() {

    private val customerViewModel: CustomerViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerViewModel.subscribeCustomer(subscribeCustomer())
        customerViewModel.customerAll()
    }

    private fun subscribeCustomer() = object : StateEventSubscriber<List<Customer>> {
        override fun onIdle() {
            binding?.tvData?.text = "Idle"
        }

        override fun onLoading() {
            binding?.tvData?.text = "Loading"
        }

        override fun onFailure(throwable: Throwable) {
            binding?.tvData?.text = "Failure"
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: List<Customer>) {
            binding?.tvData?.text = data.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}