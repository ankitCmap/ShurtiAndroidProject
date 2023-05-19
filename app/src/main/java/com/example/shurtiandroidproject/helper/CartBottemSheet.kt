package com.example.shurtiandroidproject.helper

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shurtiandroidproject.databinding.ItemBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartBottomSheetDialog:BottomSheetDialogFragment() {
    lateinit var binding:ItemBottomsheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= ItemBottomsheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d=it as BottomSheetDialog
            val bottomSheet= d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior=BottomSheetBehavior.from(it)
                behavior.state=BottomSheetBehavior.STATE_EXPANDED
                
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }
    companion object {
        const val TAG = "CartBottomDialog"
    }
}