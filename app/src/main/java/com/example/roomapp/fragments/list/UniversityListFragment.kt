package com.example.roomapp.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentUniversityListBinding
import com.example.roomapp.fragments.adapters.UniListAdapter
import com.example.roomapp.model.University
import com.example.roomapp.viewmodel.UniViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class UniversityListFragment : Fragment(),UniListAdapter.OnClick {
    private lateinit var binding: FragmentUniversityListBinding
    private lateinit var uniViewModel: UniViewModel
    private var uniList = mutableListOf<University>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityListBinding.inflate(inflater,container,false)

        uniViewModel = ViewModelProvider(this)[UniViewModel::class.java]
        val adapter = UniListAdapter()
        val recyclerView = binding.uniListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        try {
            uniViewModel.readAllUniversity.observe(viewLifecycleOwner) {
                Log.e("nlogdata",it.toString())
                uniList = it.toMutableList()
                adapter.setUniversity(it,this)
            }
        }catch (e:Exception){
            Log.e("nloge",e.toString())
        }

        val view = binding.root
        return view
    }

    override fun onClickUni(id: Int) {
        Toast.makeText(requireContext(),"Clicked: $id",Toast.LENGTH_SHORT).show()
        for (i in uniList.indices){
            if (uniList[i].id == id){
                val updatedUniList = uniList[i]
                Toast.makeText(requireContext(),updatedUniList.uni_name,Toast.LENGTH_SHORT).show()
            }
        }

    }
}