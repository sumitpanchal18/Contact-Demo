package com.sumit.daggerHiltStructure.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sumit.daggerHiltStructure.databinding.FragmentUserDetailBinding
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getLong("user_id") ?: 0
        val userName = arguments?.getString("user_name") ?: ""
        val userEmail = arguments?.getString("user_email") ?: ""
        val userPhone = arguments?.getString("user_phone") ?: ""

        binding.etName.setText(userName)
        binding.etEmail.setText(userEmail)
        binding.etPhone.setText(userPhone)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.btnUpdate.setOnClickListener {
            val updatedUser = User(
                id = userId,
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                phone = binding.etPhone.text.toString()
            )
            viewModel.updateUser(updatedUser)
            Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteUser(User(id = userId, name = "", email = "", phone = ""))
            Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}
