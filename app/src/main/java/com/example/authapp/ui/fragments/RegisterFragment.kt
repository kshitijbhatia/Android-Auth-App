package com.example.authapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.authapp.R
import com.example.authapp.databinding.FragmentRegisterBinding
import com.example.authapp.ui.activities.HomeActivity
import com.example.authapp.utils.NetworkResult
import com.example.authapp.viewModel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        initObserver()

        binding.apply {
            registerButton.setOnClickListener { view ->
                val name = nameInput.text.toString()
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                viewModel.register(name, email, password)
            }

            loginText.setOnClickListener { view ->
                parentFragmentManager.beginTransaction().apply {
                    val loginFragment = LoginFragment()
                    replace(R.id.fragment_container, loginFragment)
                    commit()
                }
            }
        }
    }

    fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect { state ->
                when(state) {
                    is NetworkResult.Initial -> {

                    }
                    is NetworkResult.Success -> {
                        val intent = Intent(context, HomeActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    is NetworkResult.Failure -> {
                        Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}