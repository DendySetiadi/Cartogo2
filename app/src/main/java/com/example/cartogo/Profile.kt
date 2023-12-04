package com.example.cartogo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cartogo.ProfileView
import com.example.cartogo.R
import com.example.cartogo.UbahProfile
import com.example.cartogo.databinding.FragmentProfileBinding

class Profile : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ProfileView::class.java)

        viewModel.profileImageUri.observe(viewLifecycleOwner, Observer { uri -> binding.profill.setImageURI(uri) })

        binding.klik.setOnClickListener {
            // Pindah ke fragment lain (ganti dengan fragment yang sesuai)
            val fragment = UbahProfile()
            replaceFragment(fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
        fragmentTransaction.addToBackStack(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
