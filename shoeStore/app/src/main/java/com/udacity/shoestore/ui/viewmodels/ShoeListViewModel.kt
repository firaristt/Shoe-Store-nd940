package com.udacity.shoestore.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val shoes = mutableListOf<Shoe>()

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    var shoeSize: String = ""
    var shoe: Shoe? = null

    fun initShoe() {
        shoe = Shoe("", 0.0, "", "")
        shoeSize = ""
    }

    fun addShoe() {
        shoe?.let { shoe ->
            shoe.size = if (shoeSize.toDoubleOrNull() != null) shoeSize.toDouble() else 0.0
            shoes.add(shoe)
            _shoeList.value = shoes
        }
    }

    fun clearShoes() {
        shoes.clear()
        shoeSize = ""
    }
}