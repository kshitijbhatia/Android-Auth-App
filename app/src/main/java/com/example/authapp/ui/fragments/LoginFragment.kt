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
import com.example.authapp.databinding.FragmentLoginBinding
import com.example.authapp.ui.activities.HomeActivity
import com.example.authapp.utils.NetworkResult
import com.example.authapp.viewModel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    val TAG = "LoginFragment"

    lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthenticationViewModel by viewModels<AuthenticationViewModel>()

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

        initObserver()

        binding.apply {
            loginButton.setOnClickListener { view ->
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                Log.d("response", "inside setOnClickListener 1")
                viewModel.login(email, password)
            }

            registerText.setOnClickListener { view ->
                parentFragmentManager.beginTransaction().apply {
                    val registerFragment = RegisterFragment()
                    replace(R.id.fragment_container, registerFragment)
                    commit()
                }
            }
        }
    }

    fun initObserver() {
        Log.d("response", "inside initObserver 1")
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            Log.d("response", "inside initObserver 2")
            viewModel.uiState.collect { state ->
                when(state) {
                    is NetworkResult.Initial -> {

                    }
                    is NetworkResult.Success -> {
                        Log.d("response", "inside initObserver 3")
                        val intent = Intent(context, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    is NetworkResult.Failure -> {
                        Log.d("response", "inside initObserver 4")
                        Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}