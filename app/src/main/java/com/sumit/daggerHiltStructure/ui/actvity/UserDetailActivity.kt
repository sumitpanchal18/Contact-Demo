package com.sumit.daggerHiltStructure.ui.actvity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sumit.daggerHiltStructure.databinding.ActivityUserDetailBinding
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getLongExtra("user_id", 0)
        val userName = intent.getStringExtra("user_name") ?: ""
        val userEmail = intent.getStringExtra("user_email") ?: ""
        val userPhone = intent.getStringExtra("user_phone") ?: ""

        binding.etName.setText(userName)
        binding.etEmail.setText(userEmail)
        binding.etPhone.setText(userPhone)

        binding.btnUpdate.setOnClickListener {
            val updatedUser = User(
                id = userId,
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                phone = binding.etPhone.text.toString()
            )
            viewModel.updateUser(updatedUser)
            Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnDelete.setOnClickListener {
            val userToDelete = User(id = userId, name = "", email = "", phone = "")
            viewModel.deleteUser(userToDelete)
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
