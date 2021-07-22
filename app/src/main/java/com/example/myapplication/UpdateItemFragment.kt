package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.data.entities.Item
import com.example.myapplication.databinding.FragmentUpdateItemBinding


class UpdateItemFragment : Fragment() {

    private lateinit var updateItemBinding: FragmentUpdateItemBinding
    private lateinit var itemToUpdate: Item

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateItemBinding = FragmentUpdateItemBinding.inflate(inflater, container, false)

        val callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_updateItemFragment_to_navigation_list_details2)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        //handleBackButton()

//
//        itemToUpdate = args.item
//        updateItemBinding.editItemName.setText(itemToUpdate.itemName)
//        updateItemBinding.editItemCount.setText(Integer.toString(itemToUpdate.itemCount))


        // Inflate the layout for this fragment


        return updateItemBinding.root
    }

    private fun handleBackButton() {
        // When back button is pressed we will navigate up the fragment
        // hierarchy. navigateUp will pop the fragment back stack automatically.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_navigation_shopping_to_shoppingListDetailsFragment)
                }
            }
        // We add the callback to those that are called when the back button is pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}