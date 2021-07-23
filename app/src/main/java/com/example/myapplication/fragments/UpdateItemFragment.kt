package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.viewModels.ShoppingListViewModel
import com.example.myapplication.data.entities.Item
import com.example.myapplication.databinding.FragmentUpdateItemBinding


class UpdateItemFragment : Fragment() {

    private lateinit var updateItemBinding: FragmentUpdateItemBinding
    private lateinit var itemToUpdate: Item
   // private lateinit var args: UpdateItemFragmentArgs
    private val args by navArgs<UpdateItemFragmentArgs>()
    private val listViewModel: ShoppingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateItemBinding = FragmentUpdateItemBinding.inflate(inflater, container, false)

        val callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.navigation_list_details)
            }

        }

        updateItemBinding.editItemName.setText(args.item.itemName)
        updateItemBinding.editItemCount.setText(args.item.itemCount.toString())

        handleBackButton()


        updateItemBinding.button.setOnClickListener{
            updateItem()
        }

        updateItemBinding.button4.setOnClickListener{
            deleteItem()
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


        return updateItemBinding.root
    }

    private fun updateItem(){
        val itemName = updateItemBinding.editItemName.text.toString()
        val itemCount = updateItemBinding.editItemCount.text.toString().toInt()

        if(updateItemBinding.editItemCount.text.isNotEmpty() || updateItemBinding.editItemName.text.isNotEmpty()) {
            val updatedItem = Item(args.item.itemID, itemName, itemCount, args.item.listID)
            listViewModel.updateItem(updatedItem)
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
    private fun deleteItem(){
        listViewModel.deleteItem(args.item)
        Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun handleBackButton() {
        // When back button is pressed we will navigate up the fragment
        // hierarchy. navigateUp will pop the fragment back stack automatically.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        // We add the callback to those that are called when the back button is pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}