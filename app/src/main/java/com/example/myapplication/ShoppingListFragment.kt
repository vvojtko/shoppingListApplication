package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.data.entities.relations.ShoppingListRepository
import com.example.myapplication.databinding.CardItemBinding
import com.example.myapplication.databinding.FragmentShoppingListBinding

class ShoppingListFragment : Fragment() {

    private lateinit var shoppingListFragmentBinding: FragmentShoppingListBinding
    private lateinit var listCardItemBinding: CardItemBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
   // private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private lateinit var adapter: ShoppingListRecyclerAdapter
    private val listViewModel: ShoppingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingListFragmentBinding = FragmentShoppingListBinding.inflate(inflater, container, false)

        addData()
        addListsRecyclerView()
        shoppingListFragmentBinding.fabButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_shopping_to_addShoppingListFragment)
        }
        val repository = ShoppingListRepository(requireActivity().application)
        //listCardItemBinding.

        return shoppingListFragmentBinding.root
    }


    private fun addData(){
        val listViewModel = listViewModel.getAllLists()
        listViewModel.observe(viewLifecycleOwner) { lists ->
            adapter.setData(lists as MutableList<ShoppingList>)
        }
    }

    private fun addListsRecyclerView() {
        val listLists = shoppingListFragmentBinding.recyclerView
        listLists.hasFixedSize()
        val linearLayoutManager = LinearLayoutManager(context)
        listLists.layoutManager = linearLayoutManager

        adapter = ShoppingListRecyclerAdapter(context)
        listLists.adapter = adapter
    }

}