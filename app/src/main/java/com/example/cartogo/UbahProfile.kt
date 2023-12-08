package com.example.cartogo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartogo.databinding.FragmentUbahProfileBinding

class UbahProfile : Fragment(R.layout.fragment_ubah_profile) {
    private var _binding: FragmentUbahProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profil: ImageView
    private lateinit var gantiProfil: Button
    private lateinit var profileManager: ProfileManager
    private lateinit var viewModel: ProfileView


    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUbahProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProfileView::class.java)
        viewModel.profileManager = ProfileManager(requireContext())
        profileManager = ProfileManager(requireContext())
        binding.profil.setImageURI(profileManager.getProfileImageUri())
        binding.gantiProfil.setOnClickListener {
            openGallery()
        }

        binding.editnama.setText(viewModel.profileManager?.userName)
        binding.editemail.setText(viewModel.profileManager?.userEmail)
        binding.edittelepon.setText(viewModel.profileManager?.userTelepon)
        binding.editjk.setText(viewModel.profileManager?.userJk)

        binding.simpan.setOnClickListener {
            val newName = binding.editnama.text.toString()
            viewModel.updateUserName(newName)
            val newEmail = binding.editemail.text.toString()
            val newTelepon = binding.edittelepon.text.toString()
            val newJk = binding.editjk.text.toString()

            viewModel.profileManager?.apply {
                userName = newName
                userEmail = newEmail
                userTelepon = newTelepon
                userJk = newJk
            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data!!
            binding.profil.setImageURI(selectedImageUri)

            profileManager.setProfileImageUri(selectedImageUri)

            viewModel.updateProfileImageUri(selectedImageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}