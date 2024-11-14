package com.sumit.daggerHiltStructure.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumit.daggerHiltStructure.R
import com.sumit.daggerHiltStructure.databinding.FragmentDashboardBinding
import com.sumit.daggerHiltStructure.ui.adapter.UserAdapter
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: UserViewModel by viewModels()
    private val userAdapter = UserAdapter(
        onItemClick = { user -> navigateToUserDetail(user) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind views
        binding = FragmentDashboardBinding.bind(view)

        setupRecyclerView()
        setupObservers()

        binding.btnFetchApiData.setOnClickListener {
            viewModel.fetchDeletedUsers()
        }

        binding.fabAddUser.setOnClickListener {
            navigateToAddUserFragment()
        }
    }

    private fun setupRecyclerView() {
        viewModel.refreshUsers()
        binding.recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.users.collect { users ->
                userAdapter.submitList(users)
            }
        }
    }

    private fun navigateToUserDetail(user: User) {
        val action = DashboardFragmentDirections.actionDashboardFragmentToUserDetailFragment(
            user.id, user.name, user.email, user.phone
        )
        findNavController().navigate(action)
    }

    private fun navigateToAddUserFragment() {
        findNavController().navigate(R.id.action_dashboardFragment_to_addUserFragment)
    }

}
