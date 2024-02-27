package com.kuzmin.animals.feature.home.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.graphics.createBitmap
import androidx.fragment.app.DialogFragment
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.databinding.FragmentInfoAnimalDialogBinding

class InfoAnimalDialogFragment : DialogFragment() {
    private var subTitle: String? = null

    private var _binding: FragmentInfoAnimalDialogBinding? = null
    private val binding: FragmentInfoAnimalDialogBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subTitle = it.getString(KEY_SUBTITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoAnimalDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSubTitle.text = subTitle

        binding.btnPositive.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val TAG = "InfoAnimalDialogFragment"

        const val KEY_SUBTITLE = "sub_title"

        fun newInstance(subTitle: String) =
            InfoAnimalDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_SUBTITLE, subTitle)
                }
            }
    }
}