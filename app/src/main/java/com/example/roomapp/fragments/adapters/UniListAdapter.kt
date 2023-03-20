package com.example.roomapp.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.University
import kotlinx.android.synthetic.main.layout_uni_list.view.*

class UniListAdapter: RecyclerView.Adapter<UniListAdapter.MyUniViewHolder>() {
    private var uniList = emptyList<University>()
    private lateinit var onClickEditBtn: OnClickEditBtn
    private lateinit var onClickDeleteBtn: OnClickDeleteBtn

    class MyUniViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       fun bindItem(currentItem: University,onClickEditBtn: OnClickEditBtn, onClickDeleteBtn: OnClickDeleteBtn){
           itemView.uniId.text = currentItem.uni_id.toString()
           itemView.uniName.text = currentItem.uni_name
           "Since: ${currentItem.uni_estd}".also { itemView.uniESTD.text = it}
           "Is Gov Approved: ${currentItem.uni_isGovApproved}".also { itemView.isGovApproved.text = it }

           itemView.btnEditUni.setOnClickListener{
               onClickEditBtn.onClickEditBtn(currentItem.id)
           }
           itemView.btnDeleteUni.setOnClickListener{
               onClickDeleteBtn.onClickDeleteBtn(currentItem.id)
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUniViewHolder {
        return MyUniViewHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.layout_uni_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyUniViewHolder, position: Int) {
        val currentItem = this.uniList[position]
        holder.bindItem(currentItem,onClickEditBtn,onClickDeleteBtn)
    }

    override fun getItemCount(): Int {
        return uniList.size
    }

    fun setUniversity(uni: List<University>,onClickEdit: OnClickEditBtn,onClickDelete: OnClickDeleteBtn){
        this.uniList = uni
        this.onClickEditBtn = onClickEdit
        this.onClickDeleteBtn = onClickDelete
        notifyItemChanged(uni.size)
    }
    interface OnClickEditBtn{
        fun onClickEditBtn(id:Int)
    }
    interface OnClickDeleteBtn{
        fun onClickDeleteBtn(id:Int)
    }
}