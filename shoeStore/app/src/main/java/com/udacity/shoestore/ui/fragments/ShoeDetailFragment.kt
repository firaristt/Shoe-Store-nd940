package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoedetailBinding
import com.udacity.shoestore.ui.viewmodels.ShoeListViewModel


class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var viewBinding: FragmentShoedetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoedetail, container, false)

        setupUI()

        return viewBinding.root
    }

    private fun setupUI() {

        viewBinding.viewModel = shoeViewModel

        // Add shoe detail when all the fields are filled
        viewBinding.saveButton.setOnClickListener {
            if (validateInputs()) {
                shoeViewModel.addShoe()
                findNavController().navigateUp()
            }
        }
        viewBinding.cancelButton.setOnClickListener{
            findNavController().navigateUp()
        }


        //When the user starts entering text into the fields, reset the error to null
        viewBinding.nameEditText.doAfterTextChanged {
            viewBinding.nameTextLayout.error = null
        }
        viewBinding.brandEditText.doAfterTextChanged {
            viewBinding.brandTextInputLayout.error = null
        }
        viewBinding.shoeSizeEditText.doAfterTextChanged {
            viewBinding.shoeSizeInputLayout.error = null
        }
        viewBinding.descriptionEditText.doAfterTextChanged {
            viewBinding.descriptionInputLayout.error = null
        }
    }

    private fun validateInputs(): Boolean {
        var validInputs = true

        if (viewBinding.nameTextLayout.editText?.text.isNullOrEmpty()) {
            validInputs = false
            viewBinding.nameTextLayout.error = resources.getString(R.string.error_string)
        }
        if (viewBinding.brandEditText.text.isNullOrEmpty()) {
            validInputs = false
            viewBinding.brandTextInputLayout.error =
                resources.getString(R.string.error_string)
        }

        if (viewBinding.shoeSizeEditText.text.isNullOrEmpty() ||
            viewBinding.shoeSizeEditText.text.toString().toDouble() !in 10.0..60.0
        ) {
            validInputs = false
            viewBinding.shoeSizeInputLayout.error =
                resources.getString(R.string.size_error_string)
        }

        if (viewBinding.descriptionEditText.text.isNullOrEmpty()) {
            validInputs = false
            viewBinding.descriptionInputLayout.error =
                resources.getString(R.string.error_string)
        }

        return validInputs
    }

}