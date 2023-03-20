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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.databinding.FragmentUniversityListBinding
import com.example.roomapp.fragments.adapters.UniListAdapter
import com.example.roomapp.fragments.dialogs.UniversityDialog
import com.example.roomapp.fragments.interfaces.OnDialogBtnClickListener
import com.example.roomapp.model.University
import com.example.roomapp.viewmodel.UniViewModel


class UniversityListFragment : Fragment(),UniListAdapter.OnClickEditBtn,UniListAdapter.OnClickDeleteBtn,OnDialogBtnClickListener{
    private lateinit var binding: FragmentUniversityListBinding
    private lateinit var uniViewModel: UniViewModel
    private var uniList = mutableListOf<University>()
    private lateinit var dialog: DialogFragment
    private var tAG = "nlog_university_fragment"
    private var count = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityListBinding.inflate(inflater, container, false)

        uniViewModel = ViewModelProvider(this)[UniViewModel::class.java]
        showUniList()

        binding.btnAddUniversity.setOnClickListener {
            addUniversity()
        }

        return binding.root
    }

    private fun showUniList() {

        count += 1
        Log.e("nlog_f_count","Show Uni List Function Called: $count")
        val adapter = UniListAdapter()
        val recyclerView = binding.uniListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        try {
            uniViewModel.readAllUniversity.observe(viewLifecycleOwner) {
                Log.e(tAG, it.toString())
                uniList = it.toMutableList()
                adapter.setUniversity(it, this,this)
                adapter.notifyItemChanged(uniList.size)
            }
        } catch (e: Exception) {
            Log.e(tAG, e.toString())
        }
    }

    private fun addUniversity() {
        dialog = UniversityDialog(this,1,null)
        dialog.show(childFragmentManager,"uniAddDialog")
    }



    override fun onBtnClick(id:Int,uniId:String,uniName:String,uniSince:String,isUniGovApproved:Boolean,btnType:Int) {
        val university = University(
            id,
            Integer.parseInt(uniId),
            uniName,
            uniSince,
            isUniGovApproved
        )
        when (btnType) {
            1 -> {
                try {
                    uniViewModel.addUniversity(university)
                    Toast.makeText(requireContext(),"University Added Successfully",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }catch (e:Exception){
                    Log.e(tAG,e.toString())
                }

            }
            2 -> {
                try {
                    uniViewModel.updateUniversity(university)
                    Toast.makeText(requireContext(),"University Updated Successfully",Toast.LENGTH_SHORT).show()
                }catch (e:Exception){
                    Log.e(tAG,e.toString())
                }

            }
            else -> {
                try {
                    uniViewModel.deleteUniversity(university)
                    Toast.makeText(requireContext()," ${university.uni_name} University Deleted",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }catch (e:Exception){
                    Log.e(tAG,e.toString())
                }
            }
        }

    }

    override fun onCrossBtnClick() {
        showUniList()
        dialog.dismiss()
    }

    override fun onClickEditBtn(id: Int) {
        for (i in uniList.indices){
            if (uniList[i].id == id){
                val updatedList = uniList[i]
                dialog = UniversityDialog(this,2,updatedList)
                dialog.show(childFragmentManager,"ForEdit")
                dialog.isCancelable = false
            }
        }
    }

    override fun onClickDeleteBtn(id: Int) {
        for (i in uniList.indices){
            if (uniList[i].id == id){
                val updatedList = uniList[i]
                dialog = UniversityDialog(this,3,updatedList)
                dialog.show(childFragmentManager,"ForDelete")
            }
        }
    }
}