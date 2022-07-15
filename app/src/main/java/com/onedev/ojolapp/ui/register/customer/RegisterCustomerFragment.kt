package com.onedev.ojolapp.ui.register.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onedev.ojolapp.R
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.databinding.FragmentRegisterCustomerBinding
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.gone
import com.onedev.ojolapp.utils.navigateUp
import com.onedev.ojolapp.utils.showToast
import com.onedev.ojolapp.utils.visible
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterCustomerFragment : ScopeFragment(), View.OnClickListener {

    private val registerCustomerViewModel: RegisterCustomerViewModel by viewModel()
    private var _binding: FragmentRegisterCustomerBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterCustomerBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerCustomerViewModel.subscribeRegisterCustomer(subscribeRegisterCustomer())

        binding?.tvLogin?.setOnClickListener(this)
        binding?.btnRegisterDriver?.setOnClickListener(this)
    }

    private fun subscribeRegisterCustomer() = object : StateEventSubscriber<Register> {
        override fun onIdle() {
            binding?.btnRegisterDriver?.text = getString(R.string.register)
        }

        override fun onLoading() {
            binding?.btnRegisterDriver?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onFailure(throwable: Throwable) {
            binding?.btnRegisterDriver?.visible()
            binding?.progressCircular?.gone()
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: Register) {
            binding?.btnRegisterDriver?.visible()
            binding?.progressCircular?.gone()
            requireContext().showToast(getString(R.string.register_success))
            requireView().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.tvLogin -> {
                v?.navigateUp()
            }
            binding?.btnRegisterDriver -> {
                val name = binding?.edtName?.text.toString()
                val username = binding?.edtUsername?.text.toString()
                val password = binding?.edtPassword?.text.toString()
                val registerRequest = RegisterRequest(name, username, password)
                registerCustomerViewModel.registerCustomer(registerRequest)
            }
        }
    }
}