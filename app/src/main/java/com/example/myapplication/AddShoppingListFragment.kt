package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.databinding.FragmentAddShoppingListBinding

class AddShoppingListFragment : Fragment() {
    private lateinit var binding : FragmentAddShoppingListBinding
    private val listViewModel: ShoppingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddShoppingListBinding.inflate(inflater, container, false)

        binding.button2.setOnClickListener {
            val listName = binding.editListName.toString()
            val newList = ShoppingList(shoppingListName = listName.toString(), progress = 0)
            listViewModel.insertList(newList)
            findNavController().navigate(R.id.action_addShoppingListFragment_to_navigation_shopping)

        }

        return binding.root
    }

}