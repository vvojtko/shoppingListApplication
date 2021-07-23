package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.data.entities.relations.ShoppingListRepository
import com.example.myapplication.data.entities.relations.ShoppingListWithItems

class ShoppingListViewModel(application : Application): AndroidViewModel(application) {

    private val repository: ShoppingListRepository = ShoppingListRepository(application)

    var listsList: LiveData<MutableList<ShoppingList>> = repository.getAllLists()


    fun insertItem(item : Item) {
        repository.insertItem(item)
    }

    fun insertList(shoppingList: ShoppingList){
        repository.insertShoppingList(shoppingList)
    }

    fun getAllLists(): LiveData<MutableList<ShoppingList>> {
        listsList = repository.getAllLists()
        return listsList
    }

    fun updateItem(item: Item){
        repository.updateItem(item)
    }
    fun deleteItem(item: Item){
        repository.deleteItem(item)
    }

    fun deleteList(listID: Int){
        repository.deleteList(listID)
    }

    fun getAllItems(listID: Int): LiveData<MutableList<Item>> {
        return repository.getAllItems(listID)
    }
}