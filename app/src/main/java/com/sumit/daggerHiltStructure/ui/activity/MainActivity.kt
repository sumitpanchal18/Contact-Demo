package com.sumit.daggerHiltStructure.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumit.daggerHiltStructure.databinding.ActivityMainBinding
import com.sumit.daggerHiltStructure.ui.adapter.UserAdapter
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    private val userAdapter = UserAdapter(
        onItemClick = { user -> navigateToUserDetailActivity(user) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()

        binding.btnFetchApiData.setOnClickListener {
            viewModel.fetchDeletedUsers()
        }

        binding.fabAddUser.setOnClickListener {
            navigateToAddUserActivity()
        }
    }

    private fun setupRecyclerView() {
        viewModel.refreshUsers()
        binding.recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.users.collect { users ->
                userAdapter.submitList(users)
            }
        }
    }

    private fun navigateToUserDetailActivity(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("user_id", user.id)
        intent.putExtra("user_name", user.name)
        intent.putExtra("user_email", user.email)
        intent.putExtra("user_phone", user.phone)
        startActivity(intent)
    }

    private fun navigateToAddUserActivity() {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
    }
}
