package com.example.roomapp.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentAddUniversityBinding
import com.example.roomapp.model.University
import com.example.roomapp.viewmodel.UniViewModel

class AddUniversityFragment : Fragment() {
    private lateinit var binding: FragmentAddUniversityBinding
    private lateinit var uniViewModel: UniViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUniversityBinding.inflate(inflater,container,false)
        val view = binding.root
        uniViewModel = ViewModelProvider(this).get(UniViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
            insertUniversityData()
        }
        binding.btnViewUniversity.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_addUniversityFragment_to_universityListFragment)
        }
        return view
    }

    private fun insertUniversityData() {
        var isGovApproved = false
        if (binding.isGovApproved.isChecked){
            isGovApproved = true
        }

        val university = University(
            0,
            Integer.parseInt(binding.etUniId.text.toString()),
            binding.etUniName.text.toString(),
            binding.etUniESTD.text.toString(),
            isGovApproved
        )
        uniViewModel.addUniversity(university)
        Toast.makeText(requireContext(),"University Added Successfully",Toast.LENGTH_LONG).show()
    }
}