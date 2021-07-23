package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.relations.ShoppingListWithItems
import com.example.myapplication.databinding.CardItemBinding

class ShoppingListDetailsAdapter (
        private val context: Context?,

        ) : RecyclerView.Adapter<ShoppingListDetailsAdapter.ViewHolder>() {


    private var dataSet = ArrayList<Item>()

   // private val dataSet = MutableList<ShoppingList>()
    var clickListener: View.OnClickListener? = null
    inner class ViewHolder(
        itemView: View,
        val imageView: ImageView,
        val itemName: TextView,
        val itemCount: TextView
    ): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(item: Item) {
            imageView.setImageResource(R.drawable.icons_shoppingbasket)
            itemName.text = item.itemName
            itemCount.text = item.itemCount.toString()
        }
    }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val shoppingListDetailsBinding = CardItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return ViewHolder(
                shoppingListDetailsBinding.itemCard,
                shoppingListDetailsBinding.itemImage,
                shoppingListDetailsBinding.itemName,
                shoppingListDetailsBinding.itemCount


            )
        }

    fun setData(dataSett: ArrayList<Item>) {

        this.dataSet = dataSett
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ShoppingListDetailsAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
        holder.itemView.setOnClickListener{
            val action = ShoppingListDetailsFragmentDirections.actionNavigationListDetailsToUpdateItemFragment(dataSet[position])
          //  holder.imageView.findNavController().navigate(R.id.updateItemFragment)
            holder.itemView.findNavController().navigate(action)
        }
    }
}