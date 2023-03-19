package com.example.roomapp.fragments.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.LayoutUniversityDialogBinding
import com.example.roomapp.fragments.interfaces.OnDialogBtnClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UniversityDialog(onBtnClickListener: OnDialogBtnClickListener,btnType: String): AppCompatDialogFragment( ) {
    private var binding: LayoutUniversityDialogBinding? = null
    private var OnClick = onBtnClickListener
    private var btnType = ""
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        dialog!!.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_common);
//        return inflater.inflate(R.layout.layout_university_dialog, container, false)
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutUniversityDialogBinding.inflate(
            LayoutInflater.from(
                context
            )
        )
        val builder = context?.let { MaterialAlertDialogBuilder(it, R.style.myCustomDialog) }
        builder?.background = ColorDrawable(Color.TRANSPARENT)
        builder?.setView(binding!!.root)
        //OP

        var isGovApproved = false
        if (binding!!.isGovApprovedCheckbox.isChecked){
            isGovApproved = true
        }
        binding!!.button.setOnClickListener{
            OnClick.onBtnClick(binding!!.etUnID.text.toString(),binding!!.etUniName.text.toString(),
            binding!!.etUniESTD.text.toString(),isGovApproved)
        }


        return builder!!.create()
        // Set custom height and width for the dialog
//        val dialog = builder!!.create()
//        val layoutParams = WindowManager.LayoutParams().apply {
//            copyFrom(dialog.window?.attributes)
//            width = (resources.displayMetrics.widthPixels * 0.98).toInt() // set width to match parent
//            height = (resources.displayMetrics.heightPixels * 0.60).toInt() // set height to 500 pixels
//        }
//        dialog.window?.attributes = layoutParams
//
//        return dialog
//        val layoutParams = WindowManager.LayoutParams().apply {
//            copyFrom(dialog.win)
//        }
    }
//    override fun onStart() {
//        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.50).toInt()
//        dialog!!.window?.setLayout(width,height)
//    }
}