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
import kotlin.math.max

class CartBottomSheetDialog() :BottomSheetDialogFragment() {
    lateinit var binding:ItemBottomsheetBinding
    private var itemSmallCount = 0
    private var itemMediumCount = 0
    private var itemLargeCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= ItemBottomsheetBinding.inflate(inflater,container,false)
        initPopupView()
        return binding.root
    }

    private fun initPopupView() {
        binding.apply {
            ivPopupClose.setOnClickListener {
                dismiss()
            }

            ivSmallDec.setOnClickListener {decreaseItemCount(1) }
            ivSmallInc.setOnClickListener { increaseItemCount(1) }

            ivMediumDec.setOnClickListener { decreaseItemCount(2) }
            ivMediumInc.setOnClickListener { increaseItemCount(2) }

            ivLargeDec.setOnClickListener { decreaseItemCount(3) }
            ivLargeInc.setOnClickListener { increaseItemCount(3) }

        }
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

    private fun increaseItemCount(boxCount: Int) {

        when (boxCount) {
            1 -> {
                itemSmallCount++
                updateItemCountText(1)
            }
            2 -> {
                itemMediumCount++
                updateItemCountText(2)
            }
            3 -> {
                itemLargeCount++
                updateItemCountText(3)
            }
        }
    }

    private fun decreaseItemCount(boxCount: Int) {
        when (boxCount) {
            1 -> {
                itemSmallCount = max(itemSmallCount - 1, 0)
                updateItemCountText(1)
            }
            2 -> {
                itemMediumCount = max(itemMediumCount - 1, 0)
                updateItemCountText(2)
            }
            3 -> {
                itemLargeCount = max(itemLargeCount - 1, 0)
                updateItemCountText(3)
            }
        }

    }

    private fun updateItemCountText(boxCount: Int) {
        when (boxCount) {
            1 -> {
                binding.tvSmallCount.text = itemSmallCount.toString()
               ShurtiApplication.instance?.s =itemSmallCount

            }
            2 -> {
                binding.tvMediumCount.text = itemMediumCount.toString()
                ShurtiApplication.instance?.m =itemMediumCount

            }
            3 -> {
                binding.tvLargeCount.text = itemLargeCount.toString()
                ShurtiApplication.instance?.l =itemLargeCount
            }
        }

    }
}