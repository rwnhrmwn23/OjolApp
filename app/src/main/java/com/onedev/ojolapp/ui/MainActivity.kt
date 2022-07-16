package com.onedev.ojolapp.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.onedev.ojolapp.R
import com.onedev.ojolapp.databinding.ActivityMainBinding
import com.onedev.ojolapp.utils.Constant.IS_LOGIN
import com.onedev.ojolapp.utils.getInstance
import org.koin.androidx.scope.ScopeActivity

class MainActivity : ScopeActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_main)

        if (getInstance(this@MainActivity).getBoolean(IS_LOGIN)) {
            graph.setStartDestination(R.id.mainFragment)
        } else {
            graph.setStartDestination(R.id.loginCustomerFragment)
        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}