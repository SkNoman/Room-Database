package com.example.roomapp.fragments.interfaces

interface OnDialogBtnClickListener {
    fun onBtnClick(id:Int,uniId:String,uniName:String,uniSince:String,isUniGovApproved:Boolean,btnType:Int)
    fun onCrossBtnClick()
}