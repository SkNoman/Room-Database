package com.example.roomapp.fragments.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
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
import com.example.roomapp.model.University
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.layout_uni_list.*
import kotlinx.android.synthetic.main.layout_university_dialog.view.*

class UniversityDialog(onBtnClickListener: OnDialogBtnClickListener,btnType: Int,university: University?): AppCompatDialogFragment( ) {
    private var binding: LayoutUniversityDialogBinding? = null
    private var OnClick = onBtnClickListener
    private var _btnType = btnType
    private var uniElements = university
    var isGovApproved = false
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

        //OPERATION

        when (_btnType) {
            1-> {
                binding!!.apply {
                    txtDialogType.setTextColor(Color.rgb(5, 173, 66 ))
                }
            }
            2 -> {
                binding!!.apply {
                    button.text = getString(R.string.edit)
                    button.background.setTint(Color.rgb(249, 16, 94 ))

                    txtDialogType.text = getString(R.string.edit)
                    txtDialogType.setTextColor(Color.rgb(249, 16, 94 ))
                }
            }
            else -> {
                binding!!.apply {
                    button.text = getString(R.string.delete)
                    button.background.setTint(Color.RED)

                    txtDialogType.text = getString(R.string.delete)
                    txtDialogType.setTextColor(Color.RED)

                    etUnID.inputType = InputType.TYPE_NULL
                    etUniName.inputType = InputType.TYPE_NULL
                    etUniESTD.inputType = InputType.TYPE_NULL
                    isGovApprovedCheckbox.isEnabled = false
                }
            }

        }

        binding!!.button.setOnClickListener{
            var id = 0
            if (_btnType != 1){
                id = uniElements!!.id
            }
            if (binding!!.isGovApprovedCheckbox.isChecked){ isGovApproved = true }
            OnClick.onBtnClick(id,binding!!.etUnID.text.toString(),binding!!.etUniName.text.toString(),
            binding!!.etUniESTD.text.toString(),isGovApproved, _btnType)
        }
        binding!!.btnCross.setOnClickListener{
            OnClick.onCrossBtnClick()
        }
        if (uniElements != null) {
            binding!!.etUnID.setText(uniElements!!.uni_id.toString())
            binding!!.etUniName.setText(uniElements!!.uni_name)
            binding!!.etUniESTD.setText(uniElements!!.uni_estd)
            binding!!.isGovApprovedCheckbox.isChecked = uniElements!!.uni_isGovApproved.toString() == "true"
        }


        //return builder!!.create()
        // Set custom height and width for the dialog
        val dialog = builder!!.create()
        val layoutParams = WindowManager.LayoutParams().apply {
            copyFrom(dialog.window?.attributes)
            width = (resources.displayMetrics.widthPixels * 0.98).toInt() // set width to match parent
            height = (resources.displayMetrics.heightPixels * 0.60).toInt() // set height to 500 pixels
        }
        dialog.window?.attributes = layoutParams

        return dialog
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