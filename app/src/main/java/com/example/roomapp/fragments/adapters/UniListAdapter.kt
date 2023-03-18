package com.example.roomapp.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.University
import kotlinx.android.synthetic.main.custom_row.view.*

class UniListAdapter: RecyclerView.Adapter<UniListAdapter.MyUniViewHolder>() {
    private var uniList = emptyList<University>()
    private lateinit var onClickUni: OnClick

    class MyUniViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUniViewHolder {
        return MyUniViewHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyUniViewHolder, position: Int) {
        val currentItem = this.uniList[position]
        holder.itemView.id_txt.text = currentItem.uni_id.toString()
        holder.itemView.firstName_txt.text = currentItem.uni_name
        holder.itemView.lastName_txt.text = currentItem.uni_estd
        holder.itemView.setOnClickListener{
            onClickUni.onClickUni(currentItem.id)
        }
    }

    override fun getItemCount(): Int {
        return uniList.size
    }

    fun setUniversity(uni: List<University>,onClickUni: OnClick){
        this.uniList = uni
        this.onClickUni = onClickUni
        notifyItemChanged(uni.size)
    }

    interface OnClick{
        fun onClickUni(id:Int)
    }
}