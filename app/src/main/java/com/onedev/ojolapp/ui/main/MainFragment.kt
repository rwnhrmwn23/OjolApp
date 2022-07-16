package com.onedev.ojolapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onedev.ojolapp.R
import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.domain.model.Driver
import com.onedev.ojolapp.databinding.FragmentMainBinding
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.Constant.LOGIN_AS
import com.onedev.ojolapp.utils.getInstance
import com.onedev.ojolapp.utils.gone
import com.onedev.ojolapp.utils.showToast
import com.onedev.ojolapp.utils.visible
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : ScopeFragment() {

    private lateinit var driverAdapter: DriverAdapter
    private lateinit var customerAdapter: CustomerAdapter
    private val customerViewModel: CustomerViewModel by viewModel()
    private val driverViewModel: DriverViewModel by viewModel()
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

        val role = getInstance(requireContext()).getString(LOGIN_AS)
        if (role == getString(R.string.customer)) {
            driverAdapter = DriverAdapter()
            binding?.rvDriver?.adapter = driverAdapter
            binding?.tvList?.text = getString(R.string.list_driver)

            driverViewModel.subscribeAllDriver(subscribeAllDriver())
            driverViewModel.driverAll()

            customerViewModel.subscribeCustomer(subscribeCustomer())
            customerViewModel.customer()
        } else {
            customerAdapter = CustomerAdapter()
            binding?.rvCustomer?.adapter = customerAdapter
            binding?.tvList?.text = getString(R.string.list_driver)

            customerViewModel.subscribeAllCustomer(subscribeAllCustomer())
            customerViewModel.customerAll()

            driverViewModel.subscribeDriver(subscribeDriver())
            driverViewModel.driver()
        }
    }

    private fun subscribeAllCustomer() = object : StateEventSubscriber<List<Customer>> {
        override fun onIdle() {
            binding?.rvCustomer?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onLoading() {
            binding?.rvCustomer?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onFailure(throwable: Throwable) {
            binding?.rvCustomer?.visible()
            binding?.progressCircular?.gone()
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: List<Customer>) {
            binding?.rvCustomer?.visible()
            binding?.progressCircular?.gone()
            customerAdapter.setListData(data)
        }
    }

    private fun subscribeCustomer() = object : StateEventSubscriber<Customer> {
        override fun onIdle() {
            binding?.tvHallo?.text = "-"
            binding?.tvInfo?.text = "-"
        }

        override fun onLoading() {
            binding?.tvHallo?.text = "Loading"
            binding?.tvInfo?.text = "Loading"
        }

        override fun onFailure(throwable: Throwable) {
            binding?.tvHallo?.text = "-"
            binding?.tvInfo?.text = "-"
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: Customer) {
            binding?.tvHallo?.text = getString(R.string.hallo, data.name.toString())
            binding?.tvInfo?.text = getString(R.string.login_info, data.role.toString())
        }
    }

    private fun subscribeAllDriver() = object : StateEventSubscriber<List<Driver>> {
        override fun onIdle() {
            binding?.rvDriver?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onLoading() {
            binding?.rvDriver?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onFailure(throwable: Throwable) {
            binding?.rvDriver?.visible()
            binding?.progressCircular?.gone()
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: List<Driver>) {
            binding?.rvDriver?.visible()
            binding?.progressCircular?.gone()
            driverAdapter.setListData(data)
        }
    }

    private fun subscribeDriver() = object : StateEventSubscriber<Driver> {
        override fun onIdle() {
            binding?.tvHallo?.text = "-"
            binding?.tvInfo?.text = "-"
        }

        override fun onLoading() {
            binding?.tvHallo?.text = "Loading"
            binding?.tvInfo?.text = "Loading"
        }

        override fun onFailure(throwable: Throwable) {
            binding?.tvHallo?.text = "-"
            binding?.tvInfo?.text = "-"
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: Driver) {
            binding?.tvHallo?.text = getString(R.string.hallo, data.name.toString())
            binding?.tvInfo?.text = getString(R.string.login_info, data.role.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}