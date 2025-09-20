package com.example.authapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.authapp.R
import com.example.authapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    val TAG = "LoginFragment"

    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loginButton.setOnClickListener { view ->
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()

                Log.d(TAG, "Email: $email Password: $password")
            }

            registerText.setOnClickListener { view ->
                Log.d(TAG, "Register Text Clicked")

                parentFragmentManager.beginTransaction().apply {
                    val registerFragment = RegisterFragment()
                    replace(R.id.fragment_container, registerFragment)
                    commit()
                }
            }
        }
    }
}