package com.onedev.ojolapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.databinding.ActivityMainBinding
import com.onedev.ojolapp.event.StateEventSubscriber
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            btnLogin.setOnClickListener {
                val loginRequest = LoginRequest("japra", "1234")
                mainViewModel.loginCustomer(loginRequest)
            }
        }

        mainViewModel.subscribeLoginCustomer(subscribeLoginCustomer())
    }

    private fun subscribeLoginCustomer() = object : StateEventSubscriber<Login> {
        override fun onIdle() {
            binding.btnLogin.text = "Idle"
        }

        override fun onLoading() {
            binding.btnLogin.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE
        }

        override fun onFailure(throwable: Throwable) {
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE
            Toast.makeText(applicationContext, throwable.message.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(data: Login) {
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE
            Toast.makeText(applicationContext, data.token, Toast.LENGTH_SHORT).show()
        }

    }
}