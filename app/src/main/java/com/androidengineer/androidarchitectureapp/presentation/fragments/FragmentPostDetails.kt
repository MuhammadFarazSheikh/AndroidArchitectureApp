package com.androidengineer.androidarchitectureapp.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.androidengineer.androidarchitectureapp.databinding.FragmentPostDetailsBinding

class FragmentPostDetails : Fragment() {

    private lateinit var binding: FragmentPostDetailsBinding
    private val args: FragmentPostDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.post.let { post ->
            binding.text.text = post.body
        }

    }
}