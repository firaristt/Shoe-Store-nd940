package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        prepare()

        return viewBinding.root
    }

    private fun prepare() {

        viewBinding.usernameEditText.doAfterTextChanged {
            viewBinding.usernameTextField.error = null
        }

        viewBinding.nextButton.setOnClickListener {
            if (viewBinding.usernameEditText.text.isNullOrEmpty()) {
                viewBinding.usernameTextField.error = resources.getString(R.string.error_string)
            }else{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }
    }
}