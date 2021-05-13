package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.databinding.ItemShoelistBinding
import com.udacity.shoestore.ui.viewmodels.ShoeListViewModel


class ShoeListingFragment : Fragment() {

    private val shoeViewModel: ShoeListViewModel by activityViewModels()

    private lateinit var viewBinding: FragmentShoelistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoelist, container, false)
        prepare(inflater, container)
        return viewBinding.root
    }

    private fun prepare(inflater: LayoutInflater,
                        container: ViewGroup?,){

        //to add new shoe item, enter shoe details
        viewBinding.addShoeFab.setOnClickListener {
            shoeViewModel.initShoe()
            findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }

        //Observe for new shoe detail added, update UI by adding new shoe item view
        shoeViewModel.shoeList.observe(viewLifecycleOwner, { shoeList ->
            if (shoeList.isNotEmpty()) viewBinding.emptyListText.visibility = View.GONE
            shoeList.forEach { shoe ->
                val itemBinding: ItemShoelistBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_shoelist, container, false)
                itemBinding.shoe = shoe
                viewBinding.shoeList.addView(itemBinding.root)
            }
        })

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_overflow, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemLogout) {
            shoeViewModel.clearShoes()
            logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment())
    }

}