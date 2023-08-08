package com.lock.the.box.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lock.the.box.databinding.FragmentAccountBinding
import com.lock.the.box.ui.EditProfileActivity

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.apply {
            gotoEditProfile.setOnClickListener {
                val intent = Intent(requireActivity(), EditProfileActivity::class.java)
                intent.putExtra("ac_name", "nameText")
                intent.putExtra("ac_phone", "phoneText")
                intent.putExtra("ac_gender", "genderText")
                intent.putExtra("ac_email", "emailid")
                intent.putExtra("image", "imageUrl")
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}