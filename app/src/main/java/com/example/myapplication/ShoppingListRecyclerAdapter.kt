package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.databinding.CardShoppingListBinding

class ShoppingListRecyclerAdapter(
    private val context: Context?
   // private var dataSet: MutableList<ShoppingList>
    ) : RecyclerView.Adapter<ShoppingListRecyclerAdapter.ViewHolder>() {

   // private lateinit var mListener: onItemCLickListener
    var clickListener: View.OnClickListener? = null
    private var dataSet = ArrayList<ShoppingList>()

    inner class ViewHolder(
        itemView: View,
        val imageView: ImageView,
        val listView: TextView,
        val listProgress: TextView): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(shoppingList: ShoppingList) {
            imageView.setImageResource(R.drawable.icons_shoppingcart)
            listView.text = shoppingList.shoppingListName
            listProgress.text = shoppingList.progress.toString()
        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingListRecyclerAdapter.ViewHolder {
        val shoppingListBinding = CardShoppingListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            shoppingListBinding.shoppingListCard,
            shoppingListBinding.shoppingListImage,
            shoppingListBinding.shoppingListName,
            shoppingListBinding.listProgress

        )
    }

    fun setData(dataSet: MutableList<ShoppingList>) {
        this.dataSet = dataSet as ArrayList<ShoppingList>
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ShoppingListRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])

        holder.itemView.setOnClickListener{
            val action = dataSet[position].listID?.let { it1 ->
                ShoppingListFragmentDirections.actionNavigationShoppingToShoppingListDetailsFragment(
                    it1
                )
            }
            if (action != null) {
                holder.itemView.findNavController().navigate(action)
            }
        }

    }

    fun changeDataSet(dataSet: MutableList<ShoppingList>){
        this.dataSet = dataSet as ArrayList<ShoppingList>
        this.notifyDataSetChanged()

    }


}