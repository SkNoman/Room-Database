package com.example.roomapp.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentAddBinding
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding:FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddBinding.inflate(inflater,container,false)
        val view = binding.root
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.addBtn.text = requireArguments().getString("text")
        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val password = addAge_et.text.toString()

        if(inputCheck(firstName, lastName, password)){
            // Create User Object
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(password)
            )
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, password: String): Boolean
    {
        return when {
            firstName.isEmpty() -> {
                false
            }
            lastName.isEmpty() -> {
                false
            }
            password.isEmpty() -> {
                false
            }
            else -> true
        }
    }

}