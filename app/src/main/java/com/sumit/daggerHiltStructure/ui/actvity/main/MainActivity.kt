package com.sumit.daggerHiltStructure.ui.actvity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumit.daggerHiltStructure.R
import com.sumit.daggerHiltStructure.ui.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView  // Declare RecyclerView variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView using findViewById
        recyclerView = findViewById(R.id.recyclerView)

        // Initialize ViewModel
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Initialize Adapter with empty list
        userAdapter = UserAdapter(listOf())

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        // Observe users LiveData from ViewModel
        userViewModel.users.observe(this) { users ->
            // Update the adapter with the new list of users
            userAdapter = UserAdapter(users)
            recyclerView.adapter = userAdapter
        }

        // Fetch users from API
        userViewModel.fetchUsersFromApi()
    }
}
