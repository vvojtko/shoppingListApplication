package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.data.entities.relations.ShoppingListWithItems
import com.example.myapplication.databinding.FragmentShoppingListBinding


class ShoppingListDetailsFragment : Fragment() {

    private lateinit var shoppingList: ShoppingList
    private lateinit var adapter: ShoppingListDetailsAdapter
    private val args by navArgs<ShoppingListDetailsFragmentArgs>()
    private val listViewModel: ShoppingListViewModel by viewModels()
    private lateinit var shoppingListDetailsBinding: FragmentShoppingListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        handleBackButton()

        shoppingListDetailsBinding = FragmentShoppingListBinding.inflate(inflater, container, false)


        addData()
        addListsRecyclerView()

        return shoppingListDetailsBinding.root
    }

    private fun handleBackButton() {
        // When back button is pressed we will navigate up the fragment
        // hierarchy. navigateUp will pop the fragment back stack automatically.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.archivedShoppingFragment)
                }
            }
        // We add the callback to those that are called when the back button is pressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun addData(){
        val listViewModel = listViewModel.getAllItems(args.int)
        listViewModel.observe(viewLifecycleOwner) { items ->
            adapter.setData(items as ArrayList<Item>)
        }
    }



    private fun addListsRecyclerView() {
        val listLists = shoppingListDetailsBinding.recyclerView
        listLists.hasFixedSize()
        val linearLayoutManager = LinearLayoutManager(context)
        listLists.layoutManager  = linearLayoutManager

        adapter = ShoppingListDetailsAdapter(context)
        listLists.adapter = adapter
    }
}