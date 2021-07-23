package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.viewModels.ShoppingListViewModel
import com.example.myapplication.data.entities.Item
import com.example.myapplication.databinding.FragmentAddNewItemBinding


class AddNewItemFragment : Fragment() {

    private lateinit var binding: FragmentAddNewItemBinding
    private val listViewModel: ShoppingListViewModel by viewModels()
    private val args by navArgs<AddNewItemFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewItemBinding.inflate(inflater, container, false)



        binding.button3.setOnClickListener {
            val itemName = binding.itemName.text.toString()
            //  val itemCount = Integer.parseInt(binding.itemCount.text.toString())
            val itemCount = binding.itemCount.text.toString()
            if (isNumber(itemCount)) {
                // val itemCount = binding.itemCount.text.toString().toInt()
                val item = Item(itemName = itemName, itemCount = Integer.parseInt(itemCount), listID = args.listID)
                listViewModel.insertItem(item)
                Toast.makeText(context, "Item added Successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "Please input the correct value for Item Count!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    fun isNumber(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (ex: NumberFormatException) {
            false
        }
    }

    private fun addNewItem() {

    }
}

