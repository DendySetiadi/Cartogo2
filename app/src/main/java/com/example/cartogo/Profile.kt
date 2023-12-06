package com.example.cartogo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        viewModel.profileImageUri.observe(viewLifecycleOwner, Observer { uri -> uri?.let {
            binding.profill.setImageURI(uri)
            }
        })

        viewModel.userName.observe(viewLifecycleOwner, Observer { newValue ->
            newValue?.let {
                binding.editNamaa.setText(newValue)
            }
        })


        binding.btnPengaturan.setOnClickListener {
            try {
                // Kode yang dapat menyebabkan crash, jika ada
                replaceFragment(UbahProfile())
            } catch (e: Exception) {
                // Log pesan kesalahan beserta stack trace
                Log.e("ProfileFragment", "Error when clicking the button", e)
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
