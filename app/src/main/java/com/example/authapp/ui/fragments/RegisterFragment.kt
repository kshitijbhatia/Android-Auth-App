package com.example.authapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.authapp.R
import com.example.authapp.databinding.FragmentRegisterBinding
import com.example.authapp.viewModel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    val TAG = "RegisterFragment"

    lateinit var binding: FragmentRegisterBinding
    // Todo: Look into by keyword
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            registerButton.setOnClickListener { view ->
                val name = nameInput.text.toString()
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()

                Log.d(TAG, "Name: $name Email: $email Password: $password")

                viewModel.register(name, email, password)
            }

            loginText.setOnClickListener { view ->
                Log.d(TAG, "Login text clicked")
                parentFragmentManager.beginTransaction().apply {
                    val loginFragment = LoginFragment()
                    replace(R.id.fragment_container, loginFragment)
                    commit()
                }
            }
        }
    }
}