package com.example.cartogo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cartogo.databinding.LupaSandiBinding
import com.example.cartogo.databinding.SandiBaruBinding

class SandiBaru: AppCompatActivity() {
    private lateinit var  binding: SandiBaruBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SandiBaruBinding.inflate(layoutInflater)
        setContentView(binding.root)}

}