package com.onedev.ojolapp.ui.login.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onedev.ojolapp.R
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.databinding.FragmentLoginCustomerBinding
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.*
import com.onedev.ojolapp.utils.Constant.IS_LOGIN
import com.onedev.ojolapp.utils.Constant.LOGIN_AS
import com.onedev.ojolapp.utils.Constant.TOKEN
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginCustomerFragment : ScopeFragment(), View.OnClickListener {

    private val loginCustomerViewModel: LoginCustomerViewModel by viewModel()
    private var _binding: FragmentLoginCustomerBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginCustomerBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginCustomerViewModel.subscribeLoginCustomer(subscribeLoginCustomer())

        binding?.tvRegister?.setOnClickListener(this)
        binding?.btnLoginCustomer?.setOnClickListener(this)
        binding?.btnLoginDriver?.setOnClickListener(this)
    }

    private fun subscribeLoginCustomer() = object : StateEventSubscriber<Login> {
        override fun onIdle() {
            binding?.btnLoginCustomer?.text = getString(R.string.login)
        }

        override fun onLoading() {
            binding?.btnLoginCustomer?.gone()
            binding?.progressCircular?.visible()
        }

        override fun onFailure(throwable: Throwable) {
            binding?.btnLoginCustomer?.visible()
            binding?.progressCircular?.gone()
            requireContext().showToast(throwable.message.toString())
        }

        override fun onSuccess(data: Login) {
            binding?.btnLoginCustomer?.visible()
            binding?.progressCircular?.gone()
            getInstance(requireContext()).putBoolean(IS_LOGIN, true)
            getInstance(requireContext()).putString(TOKEN, data.token.toString())
            getInstance(requireContext()).putString(LOGIN_AS, getString(R.string.customer))
            requireContext().showToast(getString(R.string.login_success))
            findNavController().navigate(LoginCustomerFragmentDirections.actionLoginCustomerFragmentToMainFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.tvRegister -> {
                findNavController().navigate(LoginCustomerFragmentDirections.actionLoginCustomerFragmentToRegisterCustomerFragment())
            }
            binding?.btnLoginCustomer -> {
                val username = binding?.edtUsername?.text.toString()
                val password = binding?.edtPassword?.text.toString()
                val loginRequest = LoginRequest(username, password)
                loginCustomerViewModel.loginCustomer(loginRequest)
            }
            binding?.btnLoginDriver -> {
                findNavController().navigate(LoginCustomerFragmentDirections.actionLoginCustomerFragmentToLoginDriverFragment())
            }
        }
    }
}