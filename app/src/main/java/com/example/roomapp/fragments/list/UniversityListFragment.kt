package com.example.roomapp.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentUniversityListBinding
import com.example.roomapp.fragments.adapters.UniListAdapter
import com.example.roomapp.fragments.dialogs.UniversityDialog
import com.example.roomapp.fragments.interfaces.OnDialogBtnClickListener
import com.example.roomapp.model.University
import com.example.roomapp.viewmodel.UniViewModel


class UniversityListFragment : Fragment(),UniListAdapter.OnClick,OnDialogBtnClickListener{
    private lateinit var binding: FragmentUniversityListBinding
    private lateinit var uniViewModel: UniViewModel
    private var uniList = mutableListOf<University>()
    lateinit var dialog: DialogFragment
    private var TAG = "nlog_university_fragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityListBinding.inflate(inflater, container, false)

        uniViewModel = ViewModelProvider(this)[UniViewModel::class.java]
        val adapter = UniListAdapter()
        val recyclerView = binding.uniListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        try {
            uniViewModel.readAllUniversity.observe(viewLifecycleOwner) {
                Log.e(TAG, it.toString())
                uniList = it.toMutableList()
                adapter.setUniversity(it, this)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }

        binding.btmAddUniversity.setOnClickListener {
            showUniversityDialog()
        }

        return binding.root
    }

    private fun showUniversityDialog() {
        dialog = UniversityDialog(this,"1")
        dialog.show(childFragmentManager,"uniDialog")
        dialog.isCancelable = true
    }

    override fun onClickUni(id: Int) {

        for (i in uniList.indices){
            if (uniList[i].id == id){
                val updatedUniList = uniList[i]
                Toast.makeText(requireContext(),updatedUniList.uni_name,Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBtnClick(uniId:String,uniName:String,uniSince:String,isUniGovApproved:Boolean) {
        val university = University(
            0,
            Integer.parseInt(uniId),
            uniName,
            uniSince,
            isUniGovApproved
        )
        uniViewModel.addUniversity(university)
        Toast.makeText(requireContext(),"University Added Successfully",Toast.LENGTH_LONG).show()
    }
}