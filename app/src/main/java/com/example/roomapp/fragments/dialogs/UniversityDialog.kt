package com.example.roomapp.fragments.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.roomapp.R
import com.example.roomapp.databinding.LayoutUniversityDialogBinding
import com.example.roomapp.fragments.interfaces.OnDialogBtnClickListener
import com.example.roomapp.model.University
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UniversityDialog(onBtnClickListener: OnDialogBtnClickListener,btnType: Int,university: University?): AppCompatDialogFragment( ) {
    private var binding: LayoutUniversityDialogBinding? = null
    private var onClick = onBtnClickListener
    private var _btnType = btnType
    private var uniElements = university
    private var isGovApproved = false
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
            if(!checkInput(binding!!.etUnID.text.toString(),binding!!.etUniName.text.toString(),binding!!.etUniESTD.text.toString())){
                Toast.makeText(requireContext(),"Invalid Input!",Toast.LENGTH_SHORT).show()
            }else{
                var id = 0
                if (_btnType != 1){
                    id = uniElements!!.id
                }
                if (binding!!.isGovApprovedCheckbox.isChecked){ isGovApproved = true }
                onClick.onBtnClick(id,binding!!.etUnID.text.toString(),binding!!.etUniName.text.toString(),
                binding!!.etUniESTD.text.toString(),isGovApproved, _btnType)
            }



        }
        binding!!.btnCross.setOnClickListener{
            onClick.onCrossBtnClick()
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

    }

    private fun checkInput(uniId: String, uniName: String, uniEst: String):Boolean {
      return when{
          uniId.isEmpty() ->{
              false
          }
          uniName.isEmpty() ->{
              false
          }
          uniEst.isEmpty() ->{
              false
          }
          else -> true
      }
    }
}