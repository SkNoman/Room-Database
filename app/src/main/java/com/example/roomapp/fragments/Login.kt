package com.example.roomapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentLoginBinding
import com.example.roomapp.model.UserResponse
import com.example.roomapp.model.UserResponseItem
import com.example.roomapp.modules.RetroInstance
import com.example.roomapp.services.ApiService
import com.example.roomapp.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class Login : Fragment() {

    private lateinit var mUserViewModel:UserViewModel
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root
        getUserFromOnline()
        binding.btnSignUp.setOnClickListener {
            val text = "Register"
            val bundle = Bundle()
            bundle.putString("text", text)
            Navigation.findNavController(view).navigate(R.id.action_login_to_addFragment, bundle)
        }


        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            exeLogin(username, password)
        }

        binding.btnUniversity.setOnClickListener{
            findNavController().navigate(R.id.universityListFragment)
        }


        return view
    }

    private fun getUserFromOnline() {
        RetroInstance.createService(ApiService::class.java).getUserFromOnline("users").enqueue(object :
            retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
               Log.e("nlog_online_users",response.body().toString())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.localizedMessage?.let { Log.e("nlog_online_users", it.toString()) }
            }

        })
    }

    private fun exeLogin(username: String,password:String) {
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner) { user ->

            var flag = 1
            for (i in user.indices) {
                if (user[i].lastName == username && user[i].age.toString() == password) {
                    binding.etUsername.text = null
                    binding.etPassword.text = null
                    findNavController().navigate(R.id.action_login_to_listFragment)
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                    flag = 1
                    break
                } else {
                    flag = 0
                }
            }
            if (flag == 0) {
                Toast.makeText(requireContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show()
            }


        }

    }

}